package features.order.application;

import features.order.domain.NewOrderService;
import features.product.presentation.ProductPurchaseInput;
import features.user.domain.UserRepository;
import features.user.domain.VipUser;
import shared.IMailSender;

import java.util.UUID;

public class NewOrderForVipUsecase {

    private UserRepository userRepository;
    private IMailSender mailSender;
    private NewOrderService newOrderService;

    public NewOrderForVipUsecase(IMailSender mailSender) {
        this.mailSender = mailSender;
        newOrderService = new NewOrderService();
        userRepository = new UserRepository();
    }

    public void run(UUID loginUserId, ProductPurchaseInput input) {
        VipUser user = userRepository.findVipById(loginUserId);
        newOrderService.run(input.productId, user);
        mailSender.send(user.email, "VIPへの特別商品ご案内");
    }

}
