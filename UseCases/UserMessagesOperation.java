package UseCases;

import Model.IUserAccount;
import UseCases.UserUseCase.IUserManager;
import UseCases.UserUseCase.IUserOperation;
import UseCases.UserUseCase.UserManager;

/**
 * stores the operations of user's messages
 * @author Qian Tang
 * @version 1.8
 */
public class UserMessagesOperation implements IUserMessagesOperation{

    private final IUserManager userManager;
    private final IOpManager opManager;
    private final TradingSystemInterface tradingSystem;
    private static final IUserMessagesOperation userMessagesOperation = new UserMessagesOperation();

    /**
     * the constructor of class UserMessagesOperation
     */
    public UserMessagesOperation() {
        userManager = UserManager.getInstance();
        opManager = OpManager.getInstance();
        tradingSystem = TradingSystem.getInstance();
    }

    /**
     * gets instance userMessagesOperation
     * @return userMessagesOperation
     */
    public static IUserMessagesOperation getInstance(){
        return userMessagesOperation;
    }

    /**
     * Remove inviteTransNewMessage in inviteTransNewMessages
     * @param transId the id for the invited trans
     * @param inviteTransNewMessage the inviteTransNewMessage which is needed to be removed
     */
    @Override
    public void removeInviteTransNewMessage(int transId, String inviteTransNewMessage, String userId){
        IUserAccount user = userManager.getUserById(userId);
        IUserOperation properOperation = opManager.getProperOperationByUser(user);
        properOperation.getInviteTransNewMessages().remove(transId, inviteTransNewMessage);
    }

    /**
     * Add inviteTransNewMessage to inviteTransNewMessages
     * @param transId the id for the invited trans
     * @param inviteTransNewMessage the inviteTransNewMessage which is needed to be added
     */
    @Override
    public void addInviteTransNewMessage(int transId, String inviteTransNewMessage, String userId){
        IUserAccount user = userManager.getUserById(userId);
        IUserOperation properOperation = opManager.getProperOperationByUser(user);
        properOperation.getInviteTransNewMessages().put(transId, inviteTransNewMessage);

    }

    /**
     * Remove notification in notifications
     * @param notification the notification which is needed to be removed
     */
    @Override
    public void removeNotification(String notification, String userId){
        IUserAccount s = userManager.getUserById(userId);
        IUserOperation properOperation = opManager.getProperOperationByUser(s);
        properOperation.getNotifications().remove(notification);
    }

    /**
     * Add notification to notifications
     * @param notification the notification which is needed to be added
     */
    @Override
    public void addNotification(String notification, String userId){
        IUserAccount user = userManager.getUserById(userId);
        IUserOperation properOperation = opManager.getProperOperationByUser(user);
        properOperation.getNotifications().add(notification);
    }


}
