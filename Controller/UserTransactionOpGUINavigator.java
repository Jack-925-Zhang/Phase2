package Controller;

import GateWay.*;
import Model.IMeeting;
import Model.ITransaction;
import Model.IUserAccount;
import Model.ItemInterface;
import UseCases.*;
import UseCases.MeetingSystemUseCase.IMeetingSystem;
import UseCases.MeetingSystemUseCase.MeetingSystem;
import UseCases.UserUseCase.IUserManager;
import UseCases.UserUseCase.IUserOperation;
import UseCases.UserUseCase.UserManager;

public class UserTransactionOpGUINavigator {

    private static final UserTransactionOpGUINavigator userTransactionOpGUINavigator = new UserTransactionOpGUINavigator();

    private final IDataUpdater dataUpdater;
    private final IDataDeleter dataDeleter;
    private final IDataInsertion dataInsertion;
    private final IUserManager userManager;
    private final IOpManager opManager;
    private final IUserTransactionOperation userTransactionOperation;
    private final IMeetingSystem meetingSystem;

    /**
     * the constructor of class UserTransactionOpGUINavigator
     */
    public UserTransactionOpGUINavigator() {
        dataUpdater = new DataUpdater();
        dataDeleter = new DataDeleter();
        dataInsertion = new DataInsertion();
        userManager = UserManager.getInstance();
        opManager = OpManager.getInstance();
        userTransactionOperation = UserTransactionOperation.getInstance();
        meetingSystem = MeetingSystem.getInstance();


    }

    /**
     * gets instance userTransactionOpGUINavigator
     * @return userTransactionOpGUINavigator
     */
    public static UserTransactionOpGUINavigator getInstance(){
        return userTransactionOpGUINavigator;
    }


    /**
     * This method navigates the method of editing the information of
     * one transaction from the class IUserAccount. When the information of
     * one transaction needs to be edited, the class MyTradesMenu calls this method.
     //     * @param user user edits trans
     * @param trans The transaction which needs to be edited.
     * @param tradeItem The new trade item of the transaction which needs to be edited.
     * @param time The new time of the transaction which needs to be edited.
     * @param place The new place of the transaction which needs to be edited.
     * @param tradeType The new trade type of the transaction which needs to be edited.
     */
    public void editTransactionNavigator(ITransaction trans, ItemInterface tradeItem,
                                         String time, String place, String tradeType, IUserAccount user) throws Exception {
        IMeeting correspondMeeting = meetingSystem.getCorrespondMeetingByTransId(trans.getTranId());
        correspondMeeting.setPlace(place);
        correspondMeeting.setTime(time);
        correspondMeeting.setUpdated(true);
        dataUpdater.meetingsUpdater(correspondMeeting);
        if (user.getUserId().equals(trans.getStartUser().getUserId())){
            // 这个人是发起者
            trans.setStartUserWantToTradeItem(tradeItem);

            correspondMeeting.setEditStartUser(correspondMeeting.getEditStartUser()+1);
            trans.setTradeType(tradeType);

            IUserOperation properOperation = opManager.getProperOperationByUser(user);
            dataDeleter.operationDeleter(properOperation);
            userTransactionOperation.editTransaction(trans,tradeItem,time,place,tradeType,user);
            dataInsertion.operationInsertion(properOperation);
        }else {
            correspondMeeting.setEditTargetUser(correspondMeeting.getEditTargetUser()+1);
            IUserOperation properOperation = opManager.getProperOperationByUser(user);
            dataDeleter.operationDeleter(properOperation);
            userTransactionOperation.editTransaction(trans,tradeItem,time,place,tradeType,user);
            dataInsertion.operationInsertion(properOperation);

        }
        dataUpdater.transactionsUpdater(trans);


    }

    /**
     * This method navigates the method of confirming one real in-person
     * trade from the class IUserAccount. When one real in-person trade is
     * done, the class MyTradesMenu calls this method.
     *
     * @param userAccount One participator of the transaction which needs to be confirmed.
     * @param trans The transaction which needs to be confirmed.
     */
    public void confirmRealWorldTradeNavigator(IUserAccount userAccount, ITransaction trans) throws Exception {
        IMeeting correspondMeeting = meetingSystem.getCorrespondMeetingByTransId(trans.getTranId());
        dataUpdater.meetingsUpdater(correspondMeeting);
        userTransactionOperation.confirmRealWorldTrade(userAccount, trans);
        dataUpdater.transactionsUpdater(trans);
    }

    /**
     * This method navigates the method of confirming one transaction's
     * information before it occurs from the class IUserAccount. Before
     * one transaction occurs, when its information needs to be confirmed,
     * the class MyTradesMenu calls this method.
     //     * @param user user who confirms trans
     * @param trans The transaction which needs to be confirmed.
     */
    public void confirmTransNavigator(ITransaction trans) throws Exception {
        IMeeting correspondMeeting = meetingSystem.getCorrespondMeetingByTransId(trans.getTranId());
        correspondMeeting.setConfirmTrans(true);
        dataUpdater.meetingsUpdater(correspondMeeting);
        IUserAccount s = trans.getStartUser();
        IUserAccount t = trans.getTargetUser();

        dataUpdater.transactionsUpdater(trans);

        IUserOperation properOperationS = opManager.getProperOperationByUser(s);
        IUserOperation properOperationT = opManager.getProperOperationByUser(t);

        dataDeleter.operationDeleter(properOperationS);
        dataDeleter.operationDeleter(properOperationT);

        userTransactionOperation.confirmTrans(trans);
        dataInsertion.operationInsertion(properOperationS);
        dataInsertion.operationInsertion(properOperationT);

    }

    /**
     * This method navigates the method of get the final decision of this transaction
     //     * @param user user who final confirms
     * @param trans the transaction is current going
     * @param confirmation the decision of this transaction
     */
    public void finalConfirm(ITransaction trans, boolean confirmation, String userId) throws Exception {

        IMeeting correspondMeeting = meetingSystem.getCorrespondMeetingByTransId(trans.getTranId());

        if (confirmation) {
            correspondMeeting.setConfirmTrans(true);
            dataUpdater.meetingsUpdater(correspondMeeting);
            IUserAccount s = trans.getStartUser();
            IUserAccount t = trans.getTargetUser();

            IUserOperation properOperationS = opManager.getProperOperationByUser(s);
            IUserOperation properOperationT = opManager.getProperOperationByUser(t);

            dataDeleter.operationDeleter(properOperationS);
            dataDeleter.operationDeleter(properOperationT);

            userTransactionOperation.finalConfirm(trans, confirmation, userId);

            dataInsertion.operationInsertion(properOperationS);
            dataInsertion.operationInsertion(properOperationT);

        }else {

            IUserOperation properOperation = opManager.getProperOperationByUser(userManager.getUserById(userId));
            dataDeleter.operationDeleter(properOperation);
            userTransactionOperation.finalConfirm(trans, confirmation, userId);
            dataInsertion.operationInsertion(properOperation);
        }

        dataUpdater.transactionsUpdater(trans);
    }

    /**
     * This method navigates the method removing the invited transaction
     //     * @param user remove user's invited trans
     * @param transaction the transaction that is removed
     */
    public void removeInvitedTransNavigator(ITransaction transaction, String userId) throws Exception {

        IUserAccount s = userManager.getUserById(userId);
        IUserOperation properOperation = opManager.getProperOperationByUser(s);
        dataDeleter.operationDeleter(properOperation);
        userTransactionOperation.removeInvitedTrans(transaction, userId);
        dataInsertion.operationInsertion(properOperation);
    }

    /**
     * This method navigates the method receiving transaction
     //     * @param user receives user's trans
     * @param meeting the meeting which is received
     */
    public void receiveTransNavigator(IMeeting meeting, String userId) {
        userTransactionOperation.receiveTransaction(meeting, userId);
    }

    /**
     * This method navigates the method adding invited transaction
     * @param transaction the transaction which is added
     * @param userId user's id
     */
    public void addInvitedTransNavigator(ITransaction transaction, String userId) throws Exception {

        IUserAccount s = userManager.getUserById(userId);
        IUserOperation properOperation = opManager.getProperOperationByUser(s);
        dataDeleter.operationDeleter(properOperation);
        userTransactionOperation.addInvitedTrans(transaction, userId);
        dataInsertion.operationInsertion(properOperation);
    }

    /**
     * This method navigates navigated the method declineTransNavigator.
     * @param trans the transaction we want to decline
     */
    public void declineTransNavigator(ITransaction trans) throws Exception{
        IMeeting correspondMeeting = meetingSystem.getCorrespondMeetingByTransId(trans.getTranId());
        correspondMeeting.setConfirmTrans(false);
        IUserAccount s = trans.getStartUser();
        IUserAccount t = trans.getTargetUser();

        dataUpdater.transactionsUpdater(trans);

        IUserOperation properOperationS = opManager.getProperOperationByUser(s);
        IUserOperation properOperationT = opManager.getProperOperationByUser(t);

        dataDeleter.operationDeleter(properOperationS);
        dataDeleter.operationDeleter(properOperationT);


        userTransactionOperation.declineTrans(trans);

        dataInsertion.operationInsertion(properOperationS);
        dataInsertion.operationInsertion(properOperationT);

    }


    /**
     * This method navigates addUserItemRating
     * @param user the user who adds a rating for item
     * @param ItemId the id of item
     * @param rating the integer rating given by the user.
     */
    public void addUserItemRatingNavigator(IUserAccount user, int ItemId, int rating) throws Exception {
        IUserOperation properOperation = opManager.getProperOperationByUser(user);
        dataDeleter.operationDeleter(properOperation);
        userTransactionOperation.addUserItemRating(user,ItemId,rating);
        dataInsertion.operationInsertion(properOperation);
    }


    /**
     * This method navigates addConfirmedTransaction which adds the transaction which is confirmed into this user confirmedTransaction list
     * @param transId the transaction which is confirmed by user and he/she want to add
     */
    public void addConfirmedTransactionNavigator(int transId, String userId) throws Exception {
        IUserAccount s = userManager.getUserById(userId);
        IUserOperation properOperation = opManager.getProperOperationByUser(s);
        dataDeleter.operationDeleter(properOperation);
        userTransactionOperation.addConfirmedTransaction(transId,userId);
        dataInsertion.operationInsertion(properOperation);
    }


    /**
     * This method navigates removeConfirmedTransaction which removes the transaction which is confirmed into this user confirmedTransaction list
     * @param transId the transaction which is confirmed by user and he/she want to remove
     */
    public void removeConfirmedTransactionNavigator(int transId, String userId) throws Exception {
        IUserAccount s = userManager.getUserById(userId);
        IUserOperation properOperation = opManager.getProperOperationByUser(s);
        dataDeleter.operationDeleter(properOperation);
        userTransactionOperation.removeConfirmedTransaction(transId,userId);
        dataInsertion.operationInsertion(properOperation);
    }



}
