package UseCases;

import Model.IMeeting;
import Model.ITransaction;
import Model.IUserAccount;
import Model.ItemInterface;
import UseCases.MeetingSystemUseCase.IMeetingSystem;
import UseCases.MeetingSystemUseCase.MeetingSystem;
import UseCases.UserUseCase.IUserManager;
import UseCases.UserUseCase.IUserOperation;
import UseCases.UserUseCase.UserManager;

/**
 * stores the operation of user's transaction
 * @author Qian Tang
 * @version 1.8
 */
public class UserTransactionOperation implements IUserTransactionOperation{

    IOpManager opManager;
    IUserManager userManager;
    IMeetingSystem meetingSystem;
    TradingSystemInterface tradingSystem;
    private static final IUserTransactionOperation userTransactionOperation = new UserTransactionOperation();

    /**
     * the constructor of class UserTransactionOperation
     */
    public UserTransactionOperation() {
        opManager = OpManager.getInstance();
        userManager = UserManager.getInstance();
        meetingSystem = MeetingSystem.getInstance();
        tradingSystem = TradingSystem.getInstance();
    }

    /**
     * gets instance userTransactionOperation
     * @return userTransactionOperation
     */
    public static IUserTransactionOperation getInstance(){
        return userTransactionOperation;
    }

    /**
     * Used to receive transaction from one user.
     * @param meeting the transaction information which is used for two user.
     */
    @Override
    public void receiveTransaction(IMeeting meeting, String userId) {
        opManager.getProperOperationByUser(userManager.getUserById(userId)).
                getInvitedTrans().add(meetingSystem.getTransById(meeting.getCorrespondTransId()).getTranId());
    }

    /**
     * Used to edit the specific information about transaction.
     * @param trans the transaction information which is used for two user.
     * @param tradeItem the item which is users want to trade
     * @param time the meeting time of trading
     * @param place the meeting type of trading
     * @param tradeType the trading type of this transaction
     */
    @Override
    public void editTransaction(ITransaction trans, ItemInterface tradeItem, String time, String place
            , String tradeType, IUserAccount user){
        IMeeting correspondMeeting = meetingSystem.getCorrespondMeetingByTransId(trans.getTranId());
        correspondMeeting.setPlace(place);
        correspondMeeting.setTime(time);
        correspondMeeting.setUpdated(true);
        if (user.getUserId().equals(trans.getStartUser().getUserId())){
            // 这个人是发起者
            trans.setStartUserWantToTradeItem(tradeItem);

            correspondMeeting.setEditStartUser(correspondMeeting.getEditStartUser()+1);
            trans.setTradeType(tradeType);

            IUserOperation properOperation = opManager.getProperOperationByUser(user);
            properOperation.getInvitedTrans().remove(trans.getTranId());
            this.sendTransaction(trans.getTargetUser(),trans,user); // user 是自己？
        }else {
            //这个人是接受者
            if (trans.getOneOrTwo()==2){
                trans.setTargetUserWantToTradeItem(tradeItem);
                trans.setTradeTypeForTarget(tradeType);
            }
            correspondMeeting.setEditTargetUser(correspondMeeting.getEditTargetUser()+1);
            IUserOperation properOperation = opManager.getProperOperationByUser(user);
            properOperation.getInvitedTrans().remove(trans.getTranId());
            this.sendTransaction(trans.getStartUser(),trans,user);

        }
    }

    /**
     * Send the one way transaction information to target user.
     * @param target the user who start user want to trade with.
     * @param trans  the one way transaction between this user and target user
     */
    @Override
    public void sendTransaction(IUserAccount target, ITransaction trans, IUserAccount user) {
        if (target.getOnVacation() && user.getOnVacation()) {
            this.receiveTransaction(meetingSystem.getCorrespondMeetingByTransId(trans.getTranId()),target.getUserId());
        } else {
            System.out.println("User is on vacation and not able to trade");
        }

    }

    /**
     * Confirm this transaction and change the value of confirmRealWorldTrade
     * @param user
     * @param trans the transaction information which is used for two user.
     */
    @Override
    public void confirmRealWorldTrade(IUserAccount user, ITransaction trans) {
        //if (user.getStatus().equals("Guest")) {
        //    return;
        //}
        IMeeting correspondMeeting = meetingSystem.getCorrespondMeetingByTransId(trans.getTranId());
        if(user.getUserId().equals(trans.getStartUser().getUserId())){
            correspondMeeting.setMeetingFinishedStarter(true);
        }else {
            correspondMeeting.setMeetingFinishedTarget(true);
        }


    }


    /**
     * Confirm this transaction and change the value of confirmTrans
     * @param trans the transaction information which is used for two user.

     */
    @Override
    public void confirmTrans(ITransaction trans){
        IMeeting correspondMeeting = meetingSystem.getCorrespondMeetingByTransId(trans.getTranId());
        correspondMeeting.setConfirmTrans(true);
        IUserAccount s = trans.getStartUser();
        IUserAccount t = trans.getTargetUser();

        IUserOperation properOperationS = opManager.getProperOperationByUser(s);
        IUserOperation properOperationT = opManager.getProperOperationByUser(t);

        properOperationS.getConfirmedTransaction().add(trans.getTranId());
        properOperationT.getConfirmedTransaction().add(trans.getTranId());
        properOperationS.getInvitedTrans().remove(trans.getTranId());
        properOperationT.getInvitedTrans().remove(trans.getTranId());

    }

    @Override
    public void declineTrans(ITransaction trans) {
        IMeeting correspondMeeting = meetingSystem.getCorrespondMeetingByTransId(trans.getTranId());
        correspondMeeting.setConfirmTrans(false);
        IUserAccount s = trans.getStartUser();
        IUserAccount t = trans.getTargetUser();

        IUserOperation properOperationS = opManager.getProperOperationByUser(s);
        IUserOperation properOperationT = opManager.getProperOperationByUser(t);

        properOperationS.getInvitedTrans().remove(trans.getTranId());
        properOperationT.getInvitedTrans().remove(trans.getTranId());

    }


    /**
     * Add the transaction which is confirmed into this user confirmedTransaction list
     * @param transId the transaction which is confirmed by user and he/she want to add
     */
    @Override
    public void addConfirmedTransaction(int transId, String userId){
        IUserAccount s = userManager.getUserById(userId);
        IUserOperation properOperation = opManager.getProperOperationByUser(s);
        properOperation.getConfirmedTransaction().add(transId);
    }


    /**
     * Remove the transaction which is confirmed into this user confirmedTransaction list
     * @param transId the transaction which is confirmed by user and he/she want to remove
     */
    @Override
    public void removeConfirmedTransaction(int transId, String userId){
        IUserAccount s = userManager.getUserById(userId);
        IUserOperation properOperation = opManager.getProperOperationByUser(s);
        properOperation.getConfirmedTransaction().remove(transId);
    }


    /**
     * this method is used for targetUser to perform final confirm of the transaction.
     * @param trans the transaction need to get a final confirm.
     */
    @Override
    public void finalConfirm(ITransaction trans,boolean confirmation, String userId){
        IMeeting correspondMeeting = meetingSystem.getCorrespondMeetingByTransId(trans.getTranId());

        if (confirmation) {
            correspondMeeting.setConfirmTrans(true);

            IUserAccount s = trans.getStartUser();
            IUserAccount t = trans.getTargetUser();

            IUserOperation properOperationS = opManager.getProperOperationByUser(s);
            IUserOperation properOperationT = opManager.getProperOperationByUser(t);

            properOperationS.getConfirmedTransaction().add(trans.getTranId());
            properOperationT.getConfirmedTransaction().add(trans.getTranId());
            properOperationS.getInvitedTrans().remove(trans.getTranId());
            properOperationT.getInvitedTrans().remove(trans.getTranId());

        }else {

            IUserOperation properOperation = opManager.getProperOperationByUser(userManager.getUserById(userId));
            properOperation.getInvitedTrans().remove(trans.getTranId());
        }
    }

    /**
     * Add the new invited transaction into this user's list
     * @param transaction the new invited transaction
     */
    @Override
    public void addInvitedTrans(ITransaction transaction, String userId){
        IUserAccount s = userManager.getUserById(userId);
        IUserOperation properOperation = opManager.getProperOperationByUser(s);
        properOperation.getInvitedTrans().add(transaction.getTranId());
    }


    /**
     * Remove the new invited transaction into this user's list
     * @param transaction the invited transaction which is needed to be removed
     */
    @Override
    public void removeInvitedTrans(ITransaction transaction, String userId){
        IUserAccount s = userManager.getUserById(userId);
        IUserOperation properOperation = opManager.getProperOperationByUser(s);
        properOperation.getInvitedTrans().remove(transaction.getTranId());
    }

    /**
     * @param user the user who adds a rating for item
     * @param ItemId the id of item
     * @param rating the integer rating given by the user.
     */
    @Override
    public void addUserItemRating(IUserAccount user, int ItemId, int rating){
        IUserOperation properOperation = opManager.getProperOperationByUser(user);
        if (properOperation.getUserItemRating().containsKey(ItemId)) {
            int OriginalRate = properOperation.getUserItemRating().get(ItemId);
            int NewRate = (OriginalRate + rating) / 2;
            properOperation.getUserItemRating().remove(ItemId);
            properOperation.getUserItemRating().put(ItemId, NewRate);
        }else{
            properOperation.getUserItemRating().put(ItemId, rating);
        }
    }

}
