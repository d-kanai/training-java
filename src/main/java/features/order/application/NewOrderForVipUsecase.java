package features.order.application;

import features.order.domain.NewOrder;
import features.order.presentation.NewOrderInput;
import features.user.domain.UserRepository;
import features.user.domain.VipUser;
import shared.IMailSender;

import java.util.UUID;

public class NewOrderForVipUsecase {

    private UserRepository userRepository;
    private IMailSender mailSender;
    private NewOrder newOrder;

    public NewOrderForVipUsecase(IMailSender mailSender) {
        this.mailSender = mailSender;
        newOrder = new NewOrder();
        userRepository = new UserRepository();
    }

    public void run(UUID loginUserId, NewOrderInput input) {
        VipUser user = userRepository.findVipById(loginUserId);
        newOrder.run(input.productId, user);
        mailSender.send(user.email(), "VIPへの特別商品ご案内");
    }

}
