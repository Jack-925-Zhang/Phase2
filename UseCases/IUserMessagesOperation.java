package UseCases;

import UseCases.UserUseCase.IUserManager;
import UseCases.UserUseCase.UserManager;

/**
 * stores the operations for user's messages
 * @author Tianyan Zhou
 * @version 1.8
 */
public interface IUserMessagesOperation {


    /**
     * Add notification to notifications
     * @param notification the notification which is needed to be added
     */
    void addNotification(String notification, String userId);
    /**
     * Remove notification in notifications
     * @param notification the notification which is needed to be removed
     */
    void removeNotification(String notification,String userId);

    /**
     * Add inviteTransNewMessage to inviteTransNewMessages
     * @param transId the id for the invited trans
     * @param inviteTransNewMessage the inviteTransNewMessage which is needed to be added
     */
    void addInviteTransNewMessage(int transId, String inviteTransNewMessage, String userId);

    /**
     * Remove inviteTransNewMessage in inviteTransNewMessages
     * @param transId the id for the invited trans
     * @param inviteTransNewMessage the inviteTransNewMessage which is needed to be removed
     */
    void removeInviteTransNewMessage(int transId, String inviteTransNewMessage, String userId);

}
