package Controller;

import GateWay.*;
import Model.IUserAccount;
import UseCases.*;
import UseCases.UserUseCase.IUserManager;
import UseCases.UserUseCase.IUserOperation;
import UseCases.UserUseCase.UserManager;

public class UserMessagesOpGUINavigator {

    private static final UserMessagesOpGUINavigator userMessagesOpGUINavigator = new UserMessagesOpGUINavigator();

    private final IDataDeleter dataDeleter;
    private final IDataInsertion dataInsertion;
    private final IUserManager userManager;
    private final IOpManager opManager;
    private final IUserMessagesOperation userMessagesOperation;

    public UserMessagesOpGUINavigator(){
        dataDeleter = new DataDeleter();
        dataInsertion = new DataInsertion();
        userManager = UserManager.getInstance();
        opManager = OpManager.getInstance();
        userMessagesOperation = UserMessagesOperation.getInstance();
    }

    public static UserMessagesOpGUINavigator getInstance(){
        return userMessagesOpGUINavigator;
    }

    /**
     * This method navigates the method removing inviteTransNewMessage in this user's inviteTransNewMessages.
     * @param transId the id of invited trans
     * @param inviteTransNewMessage the inviteTransNewMessage which is needed to be removed
     */
    public void removeInviteTransNewMessageNavigates(int transId, String inviteTransNewMessage, String userId) throws Exception {
        IUserAccount user = userManager.getUserById(userId);
        IUserOperation properOperation = opManager.getProperOperationByUser(user);
        dataDeleter.operationDeleter(properOperation);
        userMessagesOperation.removeInviteTransNewMessage(transId,inviteTransNewMessage,userId);
        dataInsertion.operationInsertion(properOperation);
    }

    /**
     * This method navigates the method of Add inviteTransNewMessage to inviteTransNewMessages
     * @param transId the id for the invited trans
     * @param inviteTransNewMessage the inviteTransNewMessage which is needed to be added
     */
    public void addInviteTransNewMessageNavigates(int transId, String inviteTransNewMessage, String userId) throws Exception {
        IUserAccount user = userManager.getUserById(userId);
        IUserOperation properOperation = opManager.getProperOperationByUser(user);
        dataDeleter.operationDeleter(properOperation);
        userMessagesOperation.addInviteTransNewMessage(transId,inviteTransNewMessage,userId);
        dataInsertion.operationInsertion(properOperation);

    }

    /**
     * This method navigates the method of Remove notification in notifications
     * @param notification the notification which is needed to be removed
     */
    public void removeNotificationNavigates(String notification, String userId) throws Exception {
        IUserAccount s = userManager.getUserById(userId);
        IUserOperation properOperation = opManager.getProperOperationByUser(s);
        dataDeleter.operationDeleter(properOperation);
        userMessagesOperation.removeNotification(notification, userId);
        dataInsertion.operationInsertion(properOperation);
    }

    /**
     * This method navigates the method of Add notification to notifications
     * @param notification the notification which is needed to be added
     */
    public void addNotificationNavigates(String notification, String userId) throws Exception {
        IUserAccount user = userManager.getUserById(userId);
        IUserOperation properOperation = opManager.getProperOperationByUser(user);
        dataDeleter.operationDeleter(properOperation);
        userMessagesOperation.addNotification(notification, userId);
        dataInsertion.operationInsertion(properOperation);
    }



}
