package features.order.application;

import features.order.domain.NewOrder;
import features.product.presentation.NewOrderInput;
import features.user.domain.StandardUser;
import features.user.domain.UserRepository;

import java.util.UUID;

public class NewOrderForStandardUsecase {

    private UserRepository userRepository;
    private NewOrder newOrder;

    public NewOrderForStandardUsecase() {
        userRepository = new UserRepository();
        newOrder = new NewOrder();
    }

    public void run(UUID loginUserId, NewOrderInput input) {
        StandardUser user = userRepository.findStandardByIdFromDb(loginUserId);
        newOrder.run(input.productId, user);
    }


}
