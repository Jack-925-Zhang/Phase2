package GateWay;

import GateWay.Reader.*;
import Model.*;
import UseCases.IOpManager;
import UseCases.MeetingSystemUseCase.IMeetingSystem;
import UseCases.MeetingSystemUseCase.MeetingSystem;
import UseCases.OpManager;
import UseCases.TradingSystem;
import UseCases.TradingSystemInterface;
import UseCases.UserUseCase.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * The class DataLoading is loading all the data from database and setting all managers
 * It implements the interface IDataLoading
 * @author LINNI XIE
 * @version 1.8
 */
public class DataLoading implements IDataLoading{

    private final DataLoadingHelper dataLoadingHelper;
    private final IDataOneWayTransactionReader dataOneWayTransactionReader;
    private final IDataNormalReader dataNormalReader;
    private final IDataMeetingReader dataMeetingReader;
    private final IDataItemReader dataItemReader;
    private final IDataAdminReader dataAdminReader;
    private final IDataAdminListsReader dataAdminListsReader;
    private final IDataUserListsReader dataUserListsReader;
    private final IDataTwoWayTransactionReader dataTwoWayTransactionReader;

    List<NormalUser> normalUsers;
    List<AdminUser> adminUsers;
    List<AdminOperation> adminsLists;
    List<UserOperation> usersLists;

    List<Item> items;

    List<OneWayTransaction> oneWayTransactions;
    List<TwoWayTransaction> twoWayTransactions;
    List<Meeting> meetings ;

    IUserManager userManager;
    TradingSystemInterface tradingSystem ;
    IOpManager opManager;
    IMeetingSystem meetingSystem ;

    /**
     * the constructor for class DataLoading
     */
    public DataLoading() throws Exception {
        dataLoadingHelper = DataLoadingHelper.getInstance();
        dataOneWayTransactionReader = new DataOneWayTransactionReader();
        dataAdminListsReader = new DataAdminListsReader();
        dataAdminReader = new DataAdminReader();
        dataItemReader = new DataItemReader();
        dataMeetingReader = new DataMeetingReader();
        dataNormalReader = new DataNormalReader();
        dataTwoWayTransactionReader = new DataTwoWayTransactionReader();
        dataUserListsReader = new DataUserListsReader();

        normalUsers = dataNormalReader.getNormalUsersAccounts();
        adminUsers = dataAdminReader.getAdmins();
        adminsLists = dataAdminListsReader.getAdminOperations();
        usersLists = dataUserListsReader.getUserOperations();
        items = dataItemReader.getItems();
        oneWayTransactions = dataOneWayTransactionReader.getOneWayTransactions();
        twoWayTransactions = dataTwoWayTransactionReader.getTwoWayTransactions();
        meetings = dataMeetingReader.getMeetings();

        userManager = UserManager.getInstance();
        tradingSystem = TradingSystem.getInstance();
        opManager = OpManager.getInstance();
        meetingSystem = MeetingSystem.getInstance();

    }

    /**
     * Loading the userManager
     */
    @Override
    public IUserManager accountLoading(){
        for(NormalUser user: normalUsers){
            userManager.addUserToRelatedList(user);
        }
        for(AdminUser user: adminUsers){
            userManager.addUserToRelatedList(user);
        }
        return userManager;
    }

    /**
     * loads item
     */
    @Override
    public HashMap<Integer, ItemInterface> itemLoading(){
        HashMap<Integer, ItemInterface> list = new HashMap<>();
        for(ItemInterface item: items){
            list.put(item.getItemId(),item);
        }
        return list;
    }

    /**
     * loads meeting system
     */
    @Override
    public void meetingSystemLoading(){
        HashMap<Integer, IMeeting> meets = new HashMap<>();
        HashMap<Integer, ITransaction> trans = new HashMap<>();
        for(IMeeting meeting: meetings){
            meets.put(meeting.getMeetingId(), meeting);
        }
        meetingSystem.setAllMeetings(meets);
        for(ITransaction transaction: oneWayTransactions){
            int itemId = transaction.getItemId();
            String startUserId = transaction.getStartUserId();
            String targetUserId = transaction.getTargetUserId();

            transaction.setStartUserWantToTradeItem(tradingSystem.getItemById(itemId));
            transaction.setStartUser(userManager.getUserById(startUserId));
            transaction.setTargetUser(userManager.getUserById(targetUserId));
            trans.put(transaction.getTranId(), transaction);
        }
        for(ITransaction transaction: twoWayTransactions){
            int itemId = transaction.getItemId();
            transaction.setStartUserWantToTradeItem(tradingSystem.getItemById(itemId));
            trans.put(transaction.getTranId(), transaction);
        }
        meetingSystem.setAllTransactions(trans);
    }

    /**
     * loads admins' operation lists
     */
    @Override
    public void adminsOperationsLoading(){
        HashMap<String, IAdminOperation> all = new HashMap<>();
        for(AdminOperation admin: adminsLists){

            List<ItemInterface> wishToBorrow = dataLoadingHelper.stringToListOfItem(admin.getWishToBorrowList());
            admin.setWishToBorrow(wishToBorrow);

            List<ItemInterface> wishToLend = dataLoadingHelper.stringToListOfItem(admin.getWishToLendList());
            admin.setWishToLend(wishToLend);

            List<ItemInterface> threeItems = dataLoadingHelper.stringToListOfItem(admin.getThreeItemsLists());
            admin.setThreeItems(threeItems);

            HashMap<String, Integer> tradingPartners = dataLoadingHelper.stringsToHashMapStringAndInteger(admin.getTradingPartnersKey(),admin.getTradingPartnersValue());
            admin.setTradingPartners(tradingPartners);

            List<ItemInterface> currentBorrowing = dataLoadingHelper.stringToListOfItem(admin.getCurrentBorrowingLists());
            admin.setCurrentBorrowing(currentBorrowing);

            List<ItemInterface> currentLending= dataLoadingHelper.stringToListOfItem(admin.getCurrentLendingList());
            admin.setCurrentLending(currentLending);

            HashMap<String, Integer> threePartners = dataLoadingHelper.stringsToHashMapStringAndInteger(admin.getThreePartnersKey(), admin.getThreePartnersValue());
            admin.setThreePartners(threePartners);

            List<Integer> completeTransaction = dataLoadingHelper.stringToListOfInteger(admin.getCompleteTransactionsList());
            admin.setCompleteTransaction(completeTransaction);

            HashMap<Integer, Integer> userItemRating = dataLoadingHelper.stringsToHashMapIntegerAndInteger(admin.getUserItemRatingKeys(), admin.getUserItemRatingValues());
            admin.setUserItemRating(userItemRating);

            List<String> notifications = dataLoadingHelper.stringToListOfString(admin.getNotificationsList());
            admin.setNotification(notifications);

            HashMap<Integer, String> inviteTransNewMessages = dataLoadingHelper.stringsToHashMapIntegerAndString(admin.getInviteTransNewMessagesKey(), admin.getInviteTransNewMessagesValue());
            admin.setInviteTransNewMessages(inviteTransNewMessages);

            List<Integer> confirmedTransaction = dataLoadingHelper.stringToListOfInteger(admin.getConfirmedTransactionsList());
            admin.setConfirmedTransaction(confirmedTransaction);

            List<Integer> invitedTrans = dataLoadingHelper.stringToListOfInteger(admin.getInvitedTransList());
            admin.setInvitedTrans(invitedTrans);
            HashMap<Integer, String> addingItemNewMessages = dataLoadingHelper.stringsToHashMapIntegerAndString(admin.getAddingItemNewMessagesKey(), admin.getAddingItemNewMessagesValue());
            admin.setAddingItemNewMessages(addingItemNewMessages);

            HashMap<String, String> freezeNewMessages = dataLoadingHelper.stringsToHashMapStringAndString(admin.getFreezeNewMessagesKey(),admin.getFreezeNewMessagesValue());
            admin.setFreezeNewMessages(freezeNewMessages);

            HashMap<String, String> unfreezeNewMessages = dataLoadingHelper.stringsToHashMapStringAndString(admin.getUnfreezeNewMessagesKey(),admin.getUnfreezeNewMessagesValue());
            admin.setUnfreezeNewMessages(unfreezeNewMessages);

            List<ItemInterface> requestedItems = dataLoadingHelper.stringToListOfItem(admin.getRequestedItemsDb());
            admin.setRequestedItems(requestedItems);

            List<String> addingItemRequestUsers = dataLoadingHelper.stringToListOfString(admin.getAddingRequestUsers());
            admin.setAddingItemRequestUsers(addingItemRequestUsers);

            List<String> unfrozenRequestUsers = dataLoadingHelper.stringToListOfString(admin.getUnfrozenUsers());
            admin.setUnfrozenRequestUsers(unfrozenRequestUsers);

            all.put(admin.getUserId(),admin);
        }
        opManager.setAllAdminOperations(all);
    }

    /**
     * loads users'operation lists
     */
    @Override
    public void usersListsLoading(){
        HashMap<String, IUserOperation> all = new HashMap<>();
        for(UserOperation user: usersLists){

            List<ItemInterface> wishToBorrow = dataLoadingHelper.stringToListOfItem(user.getWishToBorrowList());
            user.setWishToBorrow(wishToBorrow);

            List<ItemInterface> wishToLend = dataLoadingHelper.stringToListOfItem(user.getWishToLendList());
            user.setWishToLend(wishToLend);

            List<ItemInterface> threeItems = dataLoadingHelper.stringToListOfItem(user.getThreeItemsLists());
            user.setThreeItems(threeItems);

            HashMap<String, Integer> tradingPartners = dataLoadingHelper.stringsToHashMapStringAndInteger(user.getTradingPartnersKey(),user.getTradingPartnersValue());
            user.setTradingPartners(tradingPartners);

            List<ItemInterface> currentBorrowing = dataLoadingHelper.stringToListOfItem(user.getCurrentBorrowingLists());
            user.setCurrentBorrowing(currentBorrowing);

            List<ItemInterface> currentLending= dataLoadingHelper.stringToListOfItem(user.getCurrentLendingList());
            user.setCurrentLending(currentLending);

            HashMap<String, Integer> threePartners = dataLoadingHelper.stringsToHashMapStringAndInteger(user.getThreePartnersKey(), user.getThreePartnersValue());
            user.setThreePartners(threePartners);

            List<Integer> completeTransaction = dataLoadingHelper.stringToListOfInteger(user.getCompleteTransactionsList());
            user.setCompleteTransaction(completeTransaction);

            HashMap<Integer, Integer> userItemRating = dataLoadingHelper.stringsToHashMapIntegerAndInteger(user.getUserItemRatingKeys(), user.getUserItemRatingValues());
            user.setUserItemRating(userItemRating);

            List<String> notifications = dataLoadingHelper.stringToListOfString(user.getNotificationsList());
            user.setNotification(notifications);

            HashMap<Integer, String> inviteTransNewMessages = dataLoadingHelper.stringsToHashMapIntegerAndString(user.getInviteTransNewMessagesKey(), user.getInviteTransNewMessagesValue());
            user.setInviteTransNewMessages(inviteTransNewMessages);

            List<Integer> confirmedTransaction = dataLoadingHelper.stringToListOfInteger(user.getConfirmedTransactionsList());
            user.setConfirmedTransaction(confirmedTransaction);

            List<Integer> invitedTrans = dataLoadingHelper.stringToListOfInteger(user.getInvitedTransList());
            user.setInvitedTrans(invitedTrans);

            all.put(user.getUserId(), user);
        }
        opManager.setAllUserOperations(all);
    }
}
