package UseCases.MeetingSystemUseCase;

import Model.*;
import UseCases.*;
import UseCases.UserUseCase.IUserManager;
import UseCases.UserUseCase.IUserOperation;
import UseCases.UserUseCase.IUserWishListOperation;
import UseCases.UserUseCase.UserManager;
import UseCases.UserUseCase.UserWishListOperation;

import java.util.HashMap;

/**
 * The meetingSystem class stores all transaction, and process the realWorld trade, and
 * perform the return process of non-permanent part.
 * @author Qian Tang
 * @version Java1.8
 */

public class MeetingSystem implements IMeetingSystem {

    static final IMeetingSystem meetingSystem = new MeetingSystem();
    private IUserWishListOperation userWishListOperation;
    private HashMap<Integer, ITransaction> allTransactions;
    private final TradingSystemInterface tradingSystem;
    private HashMap<Integer,IMeeting> allMeetings;
    public IUserManager userManager;
    public IOpManager opManager;

    /**
     * the constructor of class MeetingSystem
     */
    public MeetingSystem() {
        userWishListOperation = UserWishListOperation.getInstance();
        allTransactions = new HashMap<>();
        tradingSystem = TradingSystem.getInstance();
        allMeetings = new HashMap<>();
        userManager = UserManager.getInstance();
        opManager = OpManager.getInstance();
    }

    /**
     * method to get all meetings.
     * @return map of all meetings.
     */
    @Override
    public HashMap<Integer, IMeeting> getAllMeetings() {
        return allMeetings;
    }

    public void setAllMeetings(HashMap<Integer, IMeeting> allMeetings) {
        this.allMeetings = allMeetings;
    }

    public void setAllTransactions(HashMap<Integer, ITransaction> allTransactions) {
        this.allTransactions = allTransactions;
    }

    /**
     * method to get a meeting by its id.
     * @param id meeting id
     * @return the meeting want to get
     */
    @Override
    public IMeeting getMeetingById(int id){
        return this.allMeetings.get(id);
    }

    /**
     * method to create a new Meeting.
     * @param place place to meet
     * @param time time to meet
     * @param correspondTransId corresponding Transaction id.
     */
    public void createMeeting(String place, String time, int correspondTransId){
        IMeeting newMeeting = new Meeting(place,time,correspondTransId);
        allMeetings.put(newMeeting.getMeetingId(), newMeeting);
    }

    /**
     * method to get Correspond Meeting By TransId
     *
     * @param id trans id
     * @return corresponding meeting
     */
    @Override
    public IMeeting getCorrespondMeetingByTransId(int id) {
        for (int mId: allMeetings.keySet()){
            if (allMeetings.get(mId).getCorrespondTransId()==id){
                return allMeetings.get(mId);
            }
        }
        return new Meeting();
    }

    /**
     * gets instance meetingSystem
     * @return meetingSystem
     */
    public static IMeetingSystem getInstance(){
        return meetingSystem;
    }
    /**
     * Method Used to createOneWayTran and store it.
     * @param startUser the user who start this OneWayTrans.
     * @param targetUser the user that the startUser want to trade with.
     * @param startUserWantTOTradeItem the Item startUser want to trade.
     * @param isPermanent indicate if this transaction is permanent or not.
     * @param tradeType the type of trade they want to make.
     */

    public OneWayTransaction createOneWayTrans(IUserAccount startUser, IUserAccount targetUser,
                                  ItemInterface startUserWantTOTradeItem, boolean isPermanent,
                                               String tradeType) {
        OneWayTransaction newOne = new OneWayTransaction(startUser, targetUser,
                startUserWantTOTradeItem, isPermanent, tradeType) {
            /**
             * get the rate for TwoWayItem
             *
             * @return the rate for TwoWayItem
             */
            @Override
            public int getRateForTwoWayItem() {
                return 0;
            }

            /**
             * set up the rate for twoWay item
             *
             * @param rateForTwoWayItem the new rate for twoWay item
             */
            @Override
            public void setRateForTwoWayItem(int rateForTwoWayItem) {

            }

            /**
             * not necessary for OneWayTrade so it is empty.
             * @return not necessary for OneWayTrade so it is empty.
             */
            @Override
            public ItemInterface getTargetUserWantToTradItem() {
                return null;
            }

            /**
             * not necessary for OneWayTrade so it is empty.
             * @param targetUserWantToTradItem not necessary for OneWayTrade so it is empty.
             */
            @Override
            public void setTargetUserWantToTradeItem(ItemInterface targetUserWantToTradItem) {
            }

            /**
             * not necessary for OneWayTrade so it is empty.
             * @return not necessary for OneWayTrade so it is empty.
             */
            @Override
            public String getTradeTypeForTarget() {
                return null;
            }

            /**
             * not necessary for OneWayTrade so it is empty.
             * @param tradeTypeForTarget not necessary for OneWayTrade so it is empty.
             */
            @Override
            public void setTradeTypeForTarget(String tradeTypeForTarget) {

            }
        };
        allTransactions.put(newOne.getTranId(), newOne);
        return newOne;

    }

    /**
     * Method Used to createTwoWayTran and store it.
     * @param startUser the user who start this OneWayTrans.
     * @param targetUser the user that the startUser want to trade with.
     * @param startUserWantTOTradeItem the Item startUser want to trade.
     * @param isPermanent indicate if this transaction is permanent or not.
     * @param tradeType the type of trade they want to make.
     */

    public TwoWayTransaction createTwoWayTrans(IUserAccount startUser, IUserAccount targetUser,
                                  ItemInterface startUserWantTOTradeItem, boolean isPermanent,
                                               String tradeType) {
        TwoWayTransaction newTwo = new TwoWayTransaction(startUser, targetUser, startUserWantTOTradeItem,
                isPermanent, tradeType, null);
        allTransactions.put(newTwo.getTranId(), newTwo);
        return newTwo;
    }

    /**
     * Method to help performOneWayTrans and twoWayTans to change recently 3 items trade.
     * @param starter the user who want to make this trade
     * @param target  the user who is to make this trade
     * @param tradeItem the item startUser want to trade
     */

    public void updateThreeItem(IUserAccount starter, IUserAccount target, ItemInterface tradeItem) {
        IUserOperation starterOperation = opManager.getProperOperationByUser(starter);
        IUserOperation targetOperation = opManager.getProperOperationByUser(target);
        starterOperation.getThreeItems().add(tradeItem);
        starterOperation.getThreeItems().remove(0);
        targetOperation.getThreeItems().add(tradeItem);
        targetOperation.getThreeItems().remove(0);
    }

    /**
     * Method to help performOneWayTrans and twoWayTans to updatePartnerMap
     * @param starter the user who want to make this trade
     * @param target the user who is to make this trade
     */

    public void updatePartnerMap(IUserAccount starter, IUserAccount target) {
        IUserOperation starterOperation = opManager.getProperOperationByUser(starter);

        if (starterOperation.getTradingPartners().containsKey(target.getUserId())) {
            int value = starterOperation.getTradingPartners().get(target.getUserId());
            starterOperation.getTradingPartners().put(target.getUserId(), value + 1);
        } else {
            starterOperation.getTradingPartners().put(target.getUserId(), 1);
        }
    }

    /**
     * Method to help performOneWayTrans and twoWayTans to update user's current trans.
     * @param starter the user who want to make this trade
     * @param target the user who is to make this trade
     */

    public void updateCurrentTrans(IUserAccount starter, IUserAccount target) {
        starter.setCurrentTrans(starter.getCurrentTrans() + 1);
        target.setCurrentTrans(target.getCurrentTrans() + 1);
    }

    /**
     *  Method to help performOneWayTrans and twoWayTans to update the user's number item
     *  they borrowed, and the other user's number of lent.
     * @param starter the user who want to make this trade
     * @param target the user who is to make this trade
     */

    public void updateNumBorrowed(IUserAccount starter, IUserAccount target) {
        starter.setNumBorrowed(starter.getNumBorrowed() + 1);
        target.setNumLent(target.getNumLent() + 1);
    }

    /**
     *  Method to help performOneWayTrans and twoWayTans to update the user's number item
     *  they lent, and the other user's number of borrowed.
     * @param starter the user who want to make this trade
     * @param target the user who is to make this trade
     */
    public void updateNumLent(IUserAccount starter, IUserAccount target) {
        target.setNumBorrowed(starter.getNumBorrowed() + 1);
        starter.setNumLent(target.getNumLent() + 1);
    }


    /**
     *  Method to help performOneWayTrans and twoWayTans to update the user's item
     * they borrowed, and the other user's items of lent.
     * @param starter the user who want to make this trade
     * @param target the user who is to make this trade
     * @param tradeItem the item the user want to use.
     */
    public void updateCurrentBorrowing(IUserAccount starter, IUserAccount target, ItemInterface tradeItem) {
        IUserOperation starterOperation = opManager.getProperOperationByUser(starter);
        IUserOperation targetOperation = opManager.getProperOperationByUser(target);

        starterOperation.getCurrentBorrowing().add(tradeItem);
        targetOperation.getCurrentLending().add(tradeItem);
    }

    /**
     * Method to help performOneWayTrans and twoWayTans to update the user's item
     * they lend, and the other user's items of borrowed.
     * @param starter the user who want to make this trade
     * @param target the user who is to make this trade
     * @param tradeItem the item the user want to use.
     */
    public void updateCurrentLending(IUserAccount starter, IUserAccount target, ItemInterface tradeItem) {
        IUserOperation starterOperation = opManager.getProperOperationByUser(starter);
        IUserOperation targetOperation = opManager.getProperOperationByUser(target);
        starterOperation.getCurrentBorrowing().add(tradeItem);
        targetOperation.getCurrentLending().add(tradeItem);
    }

    /**
     * Method to help performOneWayTrans and twoWayTans to update the user's item
     * they wish borrowed, and the other user's items of wishToLent.
     * @param starter the user who want to make this trade
     * @param target the user who is to make this trade
     * @param tradeItem the item the user want to use.
     */
    public void updateWishToBorrow(IUserAccount starter, IUserAccount target, ItemInterface tradeItem) throws Exception {
        userWishListOperation.removeFromWishToBorrow(starter, tradeItem);
        userWishListOperation.removeFromWishToLend(target, tradeItem);
    }

    /**
     * Method to help performOneWayTrans and twoWayTans to update the user's item
     * they wish to lent, and the other user's items of wishToBorrow.
     * @param starter the user who want to make this trade
     * @param target the user who is to make this trade
     * @param tradeItem the item the user want to use.
     */
    public void updateWishToLent(IUserAccount starter, IUserAccount target, ItemInterface tradeItem) throws Exception {

        userWishListOperation.removeFromWishToBorrow(starter, tradeItem);
        userWishListOperation.removeFromWishToLend(target, tradeItem);
    }

    /**
     * method to help to update user credit when starter receive an item from target with a temp
     * @param starter user who receive
     * @param target user who give out
     * @param TempPrice price per day
     * @param validTime keeping time
     */
    public void updateUserCreditTemp(IUserAccount starter, IUserAccount target,
                                     int TempPrice, int validTime){
        int starterBalance = starter.getCredit();
        int targetBalance = target.getCredit();
        starterBalance -= TempPrice*validTime;
        targetBalance += TempPrice*validTime;
        starter.setCredit(starterBalance);
        target.setCredit(targetBalance);
    }

    /**
     * method to help to update user credit when starter receive an item from target with a perm
     * @param starter  user who receive
     * @param target user who give out
     * @param PermPrice price for perm trade
     */
    public void updateUserCreditPerm(IUserAccount starter, IUserAccount target,
                                     int PermPrice){
        int starterBalance = starter.getCredit();
        int targetBalance = target.getCredit();
        starterBalance -= PermPrice;
        targetBalance += PermPrice;
        starter.setCredit(starterBalance);
        target.setCredit(targetBalance);
    }

    /**
     * Method to help performOneWayTrans and twoWayTans' returning process for the trans' targetUser
     * @param starter the user who want to make this trade
     * @param target the user who is to make this trade
     * @param trans the transaction between then
     */
    public void helperOneSideStarterTrans(IUserAccount starter, IUserAccount target,
                                          ITransaction trans) throws Exception {
        ItemInterface tradeItem = trans.getStartUserWantToTradeItem();
        if (trans.getTradeType().equals("borrow")) {
            updateWishToBorrow(starter, target, tradeItem);
            updateCurrentBorrowing(starter, target, tradeItem);
            updateThreeItem(starter, target, tradeItem);
            updateNumBorrowed(starter, target);
        } else {
            updateWishToLent(starter, target, tradeItem);
            updateCurrentLending(starter, target, tradeItem);
            updateThreeItem(starter, target, tradeItem);
            updateNumLent(starter, target);
        }
        updateCurrentTrans(starter, target);
        updatePartnerMap(starter, target);
        updatePartnerMap(target, starter);
    }

    /**
     * Method to help performOneWayTrans and twoWayTans' returning process for targetUser.
     * @param starter the user who want to make this trade
     * @param target the user who is to make this trade
     * @param trans the transaction between then
     */
    public void helperOneSideTargetTrans(IUserAccount starter, IUserAccount target,
                                         ITransaction trans) throws Exception {
        ItemInterface tradeItem = trans.getTargetUserWantToTradItem();
        if (trans.getTradeTypeForTarget().equals("borrow")) {
            updateWishToBorrow(starter, target, tradeItem);
            updateCurrentBorrowing(starter, target, tradeItem);
            updateThreeItem(starter, target, tradeItem);
            updateNumBorrowed(starter, target);
        } else {
            updateWishToLent(starter, target, tradeItem);
            updateCurrentLending(starter, target, tradeItem);
            updateThreeItem(starter, target, tradeItem);
            updateNumLent(starter, target);
        }
        updateCurrentTrans(starter, target);
        updatePartnerMap(starter, target);
        updatePartnerMap(target, starter);
    }

    /**
     * Method to perform OneWayTransaction
     * @param trans the transaction needed to process.
     */
    public void performOneWayTrans(OneWayTransaction trans) throws Exception {
        IUserAccount starter = trans.getStartUser();
        IUserAccount target = trans.getTargetUser();
        ItemInterface tradeItem = trans.getStartUserWantToTradeItem();
        IUserOperation starterOperation = opManager.getProperOperationByUser(starter);
        IUserOperation targetOperation = opManager.getProperOperationByUser(target);

        if (trans.isPermanent){
            if (trans.getTradeType().toLowerCase().equals("borrow")){
                updateUserCreditPerm(starter,target,tradeItem.getPriceForPerm());
            }else {
                updateUserCreditPerm(target,starter,tradeItem.getPriceForPerm());
            }
        }else {
            // temp trade
            if (trans.getTradeType().toLowerCase().equals("borrow")){
                updateUserCreditTemp(starter,target,tradeItem.getPriceForTemp(),trans.getValidTime());
            }else {
                updateUserCreditTemp(target,starter,tradeItem.getPriceForTemp(),trans.getValidTime());
            }
        }


        helperOneSideStarterTrans(starter, target, trans);
        if (trans.getRateForOneWayItem() == 0){
            return;
        }
        // below are change the rating in the user.
        if(trans.tradeType.toLowerCase().equals("borrow")){
            if (starterOperation.getUserItemRating().
                    containsKey(trans.getStartUserWantToTradeItem().getItemId())){
                int old = starterOperation.getUserItemRating().
                        get(trans.getStartUserWantToTradeItem().getItemId()); // 老分数
                old += trans.getRateForOneWayItem();
                old = old/2;
                opManager.getProperOperationByUser(starter).getUserItemRating().put(
                        trans.getStartUserWantToTradeItem().getItemId() ,old);
            }else {
                starterOperation.getUserItemRating().put(trans.getStartUserWantToTradeItem().getItemId(),
                        trans.getRateForOneWayItem());
            }
        }else {
            if (targetOperation.getUserItemRating().containsKey(trans.getStartUserWantToTradeItem().getItemId())) {
                int old = targetOperation.getUserItemRating().get(trans.getStartUserWantToTradeItem().getItemId());
                old += trans.getRateForOneWayItem();
                old = old /2;
                targetOperation.getUserItemRating().put(trans.getStartUserWantToTradeItem().getItemId(), old);
            }else {
                targetOperation.getUserItemRating().put(trans.getStartUserWantToTradeItem().getItemId(),
                        trans.getRateForOneWayItem());
            }
        }

    }

    /**
     * Method to perform TwoWayTransaction
     * @param trans  the transaction needed to process.
     */

    public void performTwoWayTrans(TwoWayTransaction trans) throws Exception {
        IUserAccount starter = trans.getStartUser();
        IUserAccount target = trans.getTargetUser();
        helperOneSideStarterTrans(starter, target, trans);
        helperOneSideTargetTrans(target, starter, trans);
        ItemInterface tradeItem = trans.getStartUserWantToTradeItem();
        ItemInterface targetTradeItem = trans.getTargetUserWantToTradItem();
        IUserOperation starterOperation = opManager.getProperOperationByUser(starter);
        IUserOperation targetOperation = opManager.getProperOperationByUser(target);

        if (trans.isPermanent){
            // starter -> target
            if (trans.getTradeType().toLowerCase().equals("borrow")){
                updateUserCreditPerm(starter,target,tradeItem.getPriceForPerm());
            }else {
                updateUserCreditPerm(target,starter,tradeItem.getPriceForPerm());
            }
            // target -> starter
            if (trans.getTradeTypeForTarget().toLowerCase().equals("borrow")){
                updateUserCreditPerm(target,starter,targetTradeItem.getPriceForPerm());
            }else {
                updateUserCreditPerm(starter,target,targetTradeItem.getPriceForPerm());
            }

        }else {
            // temp trade
            // starter -> target
            if (trans.getTradeType().toLowerCase().equals("borrow")){
                updateUserCreditTemp(starter,target,tradeItem.getPriceForTemp(),trans.getValidTime());
            }else {
                updateUserCreditTemp(target,starter,tradeItem.getPriceForTemp(),trans.getValidTime());
            }

            // target -> starter
            if (trans.getTradeTypeForTarget().toLowerCase().equals("borrow")){
                updateUserCreditTemp(target,starter,targetTradeItem.getPriceForTemp(),trans.getValidTime());
            }else {
                updateUserCreditTemp(starter,target,targetTradeItem.getPriceForTemp(),trans.getValidTime());
            }
        }


        if (trans.getRateForOneWayItem() == 0){
            return;
        }
        // below are change the rating in the user.
        if(trans.tradeType.toLowerCase().equals("borrow")){
            if (starterOperation.getUserItemRating().
                    containsKey(trans.getStartUserWantToTradeItem().getItemId())){
                int old = starterOperation.getUserItemRating().
                        get(trans.getStartUserWantToTradeItem().getItemId()); // 老分数
                old += trans.getRateForOneWayItem();
                old = old/2;
                opManager.getProperOperationByUser(starter).getUserItemRating().put(
                        trans.getStartUserWantToTradeItem().getItemId() ,old);
            }else {
                starterOperation.getUserItemRating().put(trans.getStartUserWantToTradeItem().getItemId(),
                        trans.getRateForOneWayItem());
            }
        }else {
            if (targetOperation.getUserItemRating().containsKey(trans.getStartUserWantToTradeItem().getItemId())) {
                int old = targetOperation.getUserItemRating().get(trans.getStartUserWantToTradeItem().getItemId());
                old += trans.getRateForOneWayItem();
                old = old /2;
                targetOperation.getUserItemRating().put(trans.getStartUserWantToTradeItem().getItemId(), old);
            }else {
                targetOperation.getUserItemRating().put(trans.getStartUserWantToTradeItem().getItemId(),
                        trans.getRateForOneWayItem());
            }

        }

        if (trans.getRateForTwoWayItem() == 0){
            return;
        }
        // below are change the rating in the user.
        if(trans.getTradeTypeForTarget().toLowerCase().equals("borrow")){
            if (targetOperation.getUserItemRating().
                    containsKey(trans.getTargetUserWantToTradItem().getItemId())){
                int old = targetOperation.getUserItemRating().
                        get(trans.getTargetUserWantToTradItem().getItemId()); // 老分数
                old += trans.getRateForTwoWayItem();
                old = old/2;
                opManager.getProperOperationByUser(target).getUserItemRating().put(
                        trans.getTargetUserWantToTradItem().getItemId() ,old);
            }else {
                targetOperation.getUserItemRating().put(trans.getTargetUserWantToTradItem().getItemId(),
                        trans.getRateForTwoWayItem());
            }
        }else {
            if (starterOperation.getUserItemRating().containsKey(trans.getTargetUserWantToTradItem().getItemId())) {
                int old = starterOperation.getUserItemRating().get(trans.getTargetUserWantToTradItem().getItemId());
                old += trans.getRateForTwoWayItem();
                old = old /2;
                starterOperation.getUserItemRating().put(trans.getTargetUserWantToTradItem().getItemId(), old);
            }else {
                starterOperation.getUserItemRating().put(trans.getTargetUserWantToTradItem().getItemId(),
                        trans.getRateForTwoWayItem());
            }
        }
    }

    /**
     * Method to perform OneWayTransaction's returning process
     * @param trans  the transaction needed to process.
     */


    public void performOneWayTransReturn(OneWayTransaction trans) {
        IUserAccount starter = trans.getStartUser();
        IUserAccount target = trans.getTargetUser();

        performOneSideTransReturn(starter,target,
                trans.getStartUserWantToTradeItem(),trans.getTradeType());
    }

    /**
     * Method to perform TwoWayTransaction's returning process
     * @param trans  the transaction needed to process.
     */

    public void performTwoWayTransReturn(TwoWayTransaction trans) {
        IUserAccount starter = trans.getStartUser();
        IUserAccount target = trans.getTargetUser();
        performOneSideTransReturn(starter,target,
                trans.getStartUserWantToTradeItem(),trans.getTradeType());
        performOneSideTransReturn(target, starter,
                trans.getTargetUserWantToTradItem(),trans.getTradeTypeForTarget());
    }

    /**
     * Method to help performOneWayTransReturn, performTwoWayTransReturn
     * @param starter the user need to perform return process
     * @param target the target user to perform return process
     * @param tradeItem the Item need to be return.
     * @param tradeType the tradeType they made before
     */

    public void performOneSideTransReturn(IUserAccount starter, IUserAccount target,
                                          ItemInterface tradeItem, String tradeType) {
        IUserOperation starterOperation = opManager.getProperOperationByUser(starter);
        IUserOperation targetOperation = opManager.getProperOperationByUser(target);

        if (tradeType.equals("borrow")){
            starterOperation.getCurrentBorrowing().remove(tradeItem);
            targetOperation.getCurrentLending().remove(tradeItem);
            targetOperation.getWishToLend().add(tradeItem);

        }else {
            starterOperation.getCurrentLending().remove(tradeItem);
            targetOperation.getCurrentBorrowing().remove(tradeItem);
            starterOperation.getWishToLend().add(tradeItem);

        }
    }

    /**
     * method to get all transactions.
     * @return an HashMap transactions.
     */
    @Override
    public HashMap<Integer, ITransaction> getAllTransactions() {
        return allTransactions;
    }

    /**
     * Get the transaction by this transaction id.
     * @param transId the transaction id which we want to get
     * @return the value of ITransaction
     */
    @Override
    public ITransaction getTransById(int transId){
        return allTransactions.get(transId);
    }

    /**
     * method to removeAllInvalidTrans from
     */
    @Override
    public void removeAllInvalidTrans() {

        for(Integer id:this.allMeetings.keySet()){
            if (allMeetings.get(id).getEditStartUser()==3 &&
                    allMeetings.get(id).getEditTargetUser() == 3 &&
                    !allMeetings.get(id).isConfirmTrans()
            ){
                allTransactions.remove(allMeetings.get(id).getCorrespondTransId());
                allMeetings.remove(id);

            }
        }
    }



}
