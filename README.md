README

PLEASE MAKE SURE YOUR TOMCAT HAS THE SAME BITE WITH YOUR JDK, FOR EXAMPLE IF YOU HAVE JDK 1.8 (64-Bite) then
YOUR TomCat should be (64-Bite).

group 0034

!!!!!!NOTE: If anything goes wrong, please try deleting your db files first!!!!!!

    To run our program, run the class Main in package View first.

    The first page shown is the page letting you choose the language of the pages shown to you.
The languages you can choose are English, Mandarin, Cantonese, French, Spanish, and Japanese.

    After choosing one language, the login and register page is shown to you. You need to first click
the "register" button, then enter your User id, password, and home city, then click the "complete"
button to register yourself to our program.
If the account you want to login with has already been registered, you can just enter your User id and
password, then click the "normal login" button or the "admin login" button to login.

    On the registration page, there is also a button called "Login Menu". After clicking the "Login Menu"
button, the login and register page will be shown to you again.

    After clicking the "complete" button, the login and register page is shown to you again. You can now
enter your User id and password, then click the "normal login" button or the "admin login" button to
login.
    !!!Note: If the account you want to login with is the first account registered in your computer,
             you should click the "admin login" button for administrative users.
             Otherwise, you should click the "normal login" button for normal users.

    On the login and register page, there are also buttons called "guest login", "exit", and "change language".
The "guest login" button is for guest users to login. Guest users don't need to register, and their account
won't be saved in our database.
The "exit" button is for exiting our program. After clicking the "exit" button, the process running our program
will be finished.
The "change language" button is for changing language. After clicking the "change language" button, the choose
language page is shown to you again.


1. For administrative users:

        After clicking the "admin login" button, the main menu page for administrative users is shown to you.
        On this page, buttons called "Start New Trades", "My Trades", "New Messages", "My Items", "My Account",
        "Manage User", and "Log Out" are shown.

        1. If you click the "Start New Trades" button, the Start New Trades Menu page will be shown to you.
           On this page, buttons called "Borrow Items", "Lend Items", and "Main menu" are shown.

           The "Borrow Items" button leads to the Borrow Items page where user starts borrowing item with
           searching the item user wants to borrow by item name.
           The "Lend Items" button leads to the Lend Items page where user starts lending item with
           searching the item user wants to lend by item name.
           The "Main menu" button leads to the main menu page for administrative users.

                1. If you click the "Borrow Items" button, the Borrow Items page will be shown to you.
                   On this page, buttons called "Search Items" and "Main menu" are shown.

                   The "Search Items" button leads to the Search Items page where users can search
                   the name of the item they want to borrow.
                   The "Main menu" button leads to the main menu page for administrative users.

                        1. If you click the "Search Items" button, the Search Items page will be shown to you.
                           On this page, you can enter the name of the item you want to borrow in the box
                           displayed after the text "The name of item you want to borrow: " and click the
                           "search" button to search if any user owns the item you want, and also, buttons
                           called "Borrow Items" and "Main menu" are shown.

                           The "Borrow Items" button leads to the Borrow Items page.
                           The "Main menu" button leads to the main menu page for administrative users.

                                1. If you enter the name of the item you want to borrow and then click
                                   the "search" button, the Choose Partner page will be shown to you if
                                   there is at least one user in the system owns the item you want.
                                   Otherwise, nothing will happen.
                                   On the Choose Partner page, the id of all the users who own the item you
                                   want is shown, and you can click one of the ids to choose which user you want
                                   to trade with, and also, buttons called "Borrow Items" and "Main menu" are shown.

                                   The user id buttons lead to the Trade Invitation page where you can invite a
                                   trade with the user you chose.
                                   The "Borrow Items" button leads to the Borrow Items page.
                                   The "Main menu" button leads to the main menu page for administrative users.

                                        1. If you click one of the ids, the Trade Invitation page will be shown
                                           to you.
                                           On this page, buttons called "permanent", "temporary", "one-way",
                                           "two-way", "Borrow Items", and "Main menu" are shown.

                                           If you want to start a permanent trade with the user you chose,
                                           click the "permanent" button.
                                           If you want to start a temporary trade with the user you chose,
                                           click the "temporary" button.

                                           After clicking the "permanent" button or the "temporary" button,
                                           some recommended items from your chosen user's wishlist
                                           and the "one-way" button and the "two-way" button will be shown.
                                           If you want to start a one-way trade with the user you chose,
                                           click the "one-way" button.
                                           If you want to start a two-way trade with the user you chose,
                                           click the "two-way" button.
                                           The "Borrow Items" button leads to the Borrow Items page.
                                           The "Main menu" button leads to the main menu page for administrative
                                           users.

                                                1. If you click the "one-way" button or the "two-way" button,
                                                   a trade invitation message will be sent to the user you chose.
                                                2. If you click the "Borrow Items" button, the Borrow Items page
                                                   will be shown to you.
                                                3. If you click the "Main menu" button, the Main Menu page
                                                   will be shown to you.

                                        2. If you click the "Borrow Items" button, the Borrow Items page
                                           will be shown to you.
                                        3. If you click the "Main menu" button, the Main Menu page
                                           will be shown to you.

                                2. If you click the "Borrow Items" button, the Borrow Items page will be
                                shown to you.
                                3. If you click the "Main menu" button, the Main Menu page will be shown to
                                you.

                        2. If you click the "Main menu" button, the Main Menu page will be shown to you.

                2. If you click the "Lend Items" button, the Lend Items page will be shown to you.
                   On this page, buttons called "My Available Items" and "Main menu" are shown.

                   The "My Available Items" button leads to My Available Items page where users can search
                   the name of the item they want to lend.
                   The "Main menu" button leads to the main menu page for administrative users.

                        1. If you click the "My Available Items" button, the My Available Items page will be
                           shown to you.
                           On this page, you can enter the name of the item you want to lend in the box
                           displayed after the text "The name of item you want to lend: " and click the
                           "search" button to search if any user wants the item you want to lend, and also,
                           buttons called "Lend Items" and "Main menu" are shown.

                           The "Lend Items" button leads to the Lend Items page.
                           The "Main menu" button leads to the main menu page for administrative users.

                                1. If you enter the name of the item you want to lend and then click
                                   the "search" button, the Choose Partner page will be shown to you if
                                   there is at least one user in the system wants the item you want to lend.
                                   Otherwise, nothing will happen.
                                   On the Choose Partner page, the id of all the users who want the item you
                                   want to lend is shown, and you can click one of the ids to choose which user you want
                                   to trade with, and also, buttons called "Lend Items" and "Main menu" are shown.

                                   The user id buttons lead to the Trade Invitation page where you can invite a
                                   trade with the user you chose.
                                   The "Lend Items" button leads to the Lend Items page.
                                   The "Main menu" button leads to the main menu page for administrative users.

                                        1. If you click one of the ids, the Trade Invitation page will be shown
                                           to you.
                                           On this page, buttons called "permanent", "temporary", "one-way",
                                           "two-way", "Borrow Items", and "Main menu" are shown.

                                           If you want to start a permanent trade with the user you chose,
                                           click the "permanent" button.
                                           If you want to start a temporary trade with the user you chose,
                                           click the "temporary" button.

                                           After clicking the "permanent" button or the "temporary" button,
                                           some recommended items from your chosen user's inventory
                                           and the "one-way" button and the "two-way" button will be shown.
                                           If you want to start a one-way trade with the user you chose,
                                           click the "one-way" button.
                                           If you want to start a two-way trade with the user you chose,
                                           click the "two-way" button.
                                           The "Lend Items" button leads to the Lend Items page.
                                           The "Main menu" button leads to the main menu page for administrative
                                           users.

                                                1. If you click the "one-way" button or the "two-way" button,
                                                   a trade invitation message will be sent to the user you chose.
                                                2. If you click the "Lend Items" button, the Lend Items page
                                                   will be shown to you.
                                                3. If you click the "Main menu" button, the Main Menu page
                                                   will be shown to you.

                                        2. If you click the "Lend Items" button, the Lend Items page
                                           will be shown to you.
                                        3. If you click the "Main menu" button, the Main Menu page
                                           will be shown to you.

                                2. If you click the "Lend Items" button, the Lend Items page will be
                                   shown to you.
                                3. If you click the "Main menu" button, the Main Menu page will be shown to
                                   you.

                        2. If you click the "Main menu" button, the Main Menu page will be shown to you.

                3. If you click the "Main menu" button, the Main Menu page will be shown to you.

        2. If you click the "My Trades" button, the My Trades Menu page will be shown to you.
           On this page, buttons called "My transactions", "My three most recent trading items",
           "My three most frequent trading partners", and "Main menu" are shown.

           The "My transactions" button leads to My transactions page where users can check their invited
           transactions and confirmed transactions.
           The "My three most recent trading items" button leads to My three most recent trading items page
           where the three most recent trading items are shown.
           The "My three most frequent trading partners" button leads to My three most frequent trading
           partners page where the user's three most frequent trading partners are shown.
           The "Main menu" button leads to the main menu page for administrative users.

                1. If you click the "My transactions" button, the My transactions page will be shown to you.
                   On this page, buttons called "invited transactions", "confirm transactions", "My Trades"
                   and "Main menu" are shown.

                   The "invited transactions" button leads to the invited transactions page where all
                   invited transactions are shown.
                   The "confirmed transactions" button leads to the confirmed transactions page where all
                   confirmed transactions are shown.
                   The "My Trades" button leads to the My Trades page.
                   The "Main menu" button leads to the main menu page for administrative users.

                        1. If you click the "invited transactions" button, the invited transactions page will
                           be shown to you.
                           On this page, all your invited transactions and buttons called "Edit", "Confirm",
                           "Decline", "My Trades", and "Main menu" are shown.

                           The "Edit" button leads to the choose invited transactions edit page where users can
                           choose their invited transactions to edit.
                           The "Confirm" button leads to the choose invited transactions confirm page where users
                           can choose their invited transactions to confirm.
                           The "Decline" button leads to the choose invited transactions decline page where users
                           can choose their invited transactions to decline.
                           The "My Trades" button leads to the My Trades page.
                           The "Main menu" button leads to the main menu page for administrative users.

                                1. If you click the "Edit" button, the choose invited transactions edit page will
                                   be shown to you.
                                   On this page, you can enter the id of the invited transaction you want to
                                   edit in the box after the text "Transaction id: " and then click the "Edit"
                                   button to choose to edit this transaction, and also, button called
                                   "invited transactions" is shown.

                                   The "Edit" button leads to the edit invited transaction page where users can
                                   edit one of their invited transactions.
                                   The "invited transactions" button leads to the invited transactions page.

                                        1. If you click the "Edit" button, the edit invited transactions page will
                                           be shown to you.
                                           On this page,you can enter new meeting location of this transaction in
                                           the box after the text "Meeting location: " then enter the new meeting
                                           time of this transaction in the box after the text "Meeting time: " then
                                           click the "complete" button to edit this invited transaction, and also,
                                           buttons called "My Trades" and "Main menu" are shown.

                                           The "complete" button edits this invited transaction.
                                           The "My Trades" button leads to the My Trades page.
                                           The "Main menu" button leads to the main menu page for administrative
                                           users.

                                                1. If you click the "complete" button, the invited transaction
                                                   you choose will be edited.

                                                2. If you click the "My Trades" button, the My Trades page will be
                                                   shown to you.

                                                3. If you click the "Main menu" button, the Main Menu page will be
                                                   shown to you.

                                        2. If you click the "invited transactions" button, the invited transactions
                                           page will be shown to you.

                                2. If you click the "Confirm" button, the choose invited transactions confirm page
                                   will be shown to you.
                                   On this page, you can enter the id of the invited transaction you want to
                                   confirm in the box after the text "Transaction id: " and then click the "Confirm"
                                   button to choose to confirm this transaction, and also, button called
                                   "invited transactions" is shown.

                                   The "Confirm" button leads to the confirm invited transaction page where users
                                   can confirm one of their invited transactions.
                                   The "invited transactions" button leads to the invited transactions page.

                                        1. If you click the "Confirm" button, the invited transaction you choose
                                           will be confirmed.

                                        2. If you click the "invited transactions" button, the invited transactions
                                           page will be shown to you.

                                3. If you click the "Decline" button, the choose invited transactions decline page
                                   will be shown to you.
                                   On this page, you can enter the id of the invited transaction you want to
                                   decline in the box after the text "Transaction id: " and then click the "Decline"
                                   button to choose to decline this transaction, and also, button called
                                   "invited transactions" is shown.

                                   The "Decline" button leads to the decline invited transaction page where users
                                   can decline one of their invited transactions.
                                   The "invited transactions" button leads to the invited transactions page.

                                        1. If you click the "Decline" button, the invited transaction you choose
                                           will be decline.

                                        2. If you click the "invited transactions" button, the invited transactions
                                           page will be shown to you.

                                4. If you click the "My Trades" button, the My Trades page will be shown to you.

                                5. If you click the "Main menu" button, the Main Menu page will be shown to you.

                        2. If you click the "confirmed transactions" button, the confirmed transactions page will
                           be shown to you.
                           On this page, all your invited transactions are shown, and you can enter the id of the
                           confirmed transaction you have completed in the box after the text "Transaction id: "
                           then click the "complete" button to confirm that this transaction's real world trade
                           has completed, and also, buttons called "My Trades", and "Main menu" are shown.

                           The "complete" button confirms that this transaction's real world trade has completed.
                           The "My Trades" button leads to the My Trades page.
                           The "Main menu" button leads to the main menu page for administrative users.

                                1. If you click the "complete" button, the transaction you chose will be confirmed
                                   that this transaction's real world trade has completed.

                                2. If you click the "My Trades" button, the My Trades page will be shown to you.

                                3. If you click the "Main menu" button, the Main Menu page will be shown to you.

                        3. If you click the "My Trades" button, the My Trades page will be shown to you.

                        4. If you click the "Main menu" button, the Main Menu page will be shown to you.

                2. If you click the "My three most recent trading items" button, the My three most recent trading
                   items page will be shown to you.
                   On this page, user's three most recent trading items and buttons called "My Trades" and
                   "Main menu" are shown.

                   The "My Trades" button leads to the My Trades page.
                   The "Main menu" button leads to the main menu page for administrative users.

                        1. If you click the "My Trades" button, the My Trades page will be shown to you.

                        2. If you click the "Main menu" button, the Main Menu page will be shown to you.

                3. If you click the "My three most frequent trading partners" button, the My three most frequent
                   trading partners page will be shown to you.
                   On this page, user's three most frequent trading partners and buttons called "My Trades" and
                   "Main menu" are shown.

                   The "My Trades" button leads to the My Trades page.
                   The "Main menu" button leads to the main menu page for administrative users.

                        1. If you click the "My Trades" button, the My Trades page will be shown to you.

                        2. If you click the "Main menu" button, the Main Menu page will be shown to you.

                4. If you click the "Main menu" button, the Main Menu page will be shown to you.

        3. If you click the "New Messages" button, the New Messages page will be shown to you.
           On this page, all user's notifications and all user's trade invitation messages and the button called
           "Main menu" are shown.
           Note: "user's notifications" means all new messages that do not need to be confirmed or rejected
                 sent to user.

           After clicking one of the notifications, the clicked notification will be deleted, meaning you have
           read this notification.
           Clicking any of the trade invitation messages leads to the trade invitation message page.
           The "Main menu" button leads to the main menu page for administrative users.

                1. If you click any of the notifications, the clicked notification will be deleted, meaning you
                   have read this notification.

                2. If you click any of the trade invitation messages, the trade invitation message page will
                   be shown to you.
                   On this page, the information of this invited trade and buttons called "Confirm" and "Decline"
                   will be shown.

                   The "Confirm" button confirms this invited trade, and then leads to the New Messages page.
                   The "Decline" button rejects this invited trade, and then leads to the New Messages page.

                        1. If you click the "Confirm" button, this invited trade will be confirmed, and then
                           the New Messages page will be shown to you.

                        2. If you click the "Decline" button, this invited trade will be rejected, and then
                           the New Messages page will be shown to you.

                3. If you click the "Main menu" button, the Main Menu page will be shown to you.

        4. If you click the "My Items" button, the My Items Menu page will be shown to you.
           On this page, buttons called "My Lent Items", "My Not Lent Items", "Borrowed Items", "Add Item",
           "Remove Item", "Wish List", and "Main menu" are shown.

           The "My Lent Items" buttons leads to the My Lent Items page where all user's lent items are shown.
           The "My Not Lent Items" buttons leads to the My Not Lent Items page where all user's not lent items
           are shown.
           The "Borrowed Items" buttons leads to the Borrowed Items page where all user's borrowed items are shown.
           The "Add Item" buttons leads to the Add Item page where user can add item to their inventory or wishlist.
           The "Remove Item" buttons leads to the Remove Item page where user can remove item from their inventory
           or wishlist.
           The "Wish List" buttons leads to the Add Item page where user can add item to their inventory or wishlist.
           The "Main menu" button leads to the main menu page for administrative users.

                1. If you click the "My Lent Items" button, the My Lent Items page will be shown to you.
                   On this page, the names of all your lent items will be shown.

                2. If you click the "My Not Lent Items" button, the My Not Lent Items page will be shown to you.
                   On this page, the names of all your not lent items will be shown.

                3. If you click the "Borrowed Items" button, the Borrowed Items page will be shown to you.
                   On this page, the names of all your borrowed items will be shown.

                4. If you click the "Add Item" button, the Add Item page will be shown to you.
                   On this page, buttons called "Add New Item", "Add To Wishlist", "My Items", and "Main menu" are
                   shown.

                   The "Add New Item" button leads to the Add New Item page where user can add item to their wish to
                   lend list (your inventory). The administrative users are able to create new items and add them to
                   the system inventory; whereas the normal users have to send requests to administrative users about
                   the items that they want to add to their lending list and also the system inventory.
                   The "Add To Wishlist" button leads to the Add To Wishlist page where user can add item their
                   wish to borrow list (your wishlist). You can select the items already existing in the system
                   inventory to the user wishlist.
                   The "My Items" button leads back to the My Items Menu page.
                   The "Main menu" button leads to the main menu page for administrative users.

                        1. If you click the "Add New Item" button, the Add New Item page will be shown to you.
                           On this page, you can enter the name of the item you want to add to your inventory in
                           the box after the text "The name of item you want to add: " and enter the description
                           of the item you want to add to your inventory in the box after the text "The description
                           of item you want to add: " and then click the "add" button to request for adding the item
                           you want to add to your inventory, and also, buttons called "My Items" and "Main menu"
                           are shown.

                           After entering the name and description of the item user want to add to his/her inventory
                           in the boxes, the "add" button requests for adding this item user want to add to his/her
                           inventory.
                           The "My Items" button leads to the My Items Menu page.
                           The "Main menu" button leads to the main menu page for administrative users.

                                1. If you click the "add" button after entering the name and description of the item
                                   you want to add to your inventory in the boxes, this item you want will be added
                                   to your inventory if you are the initial administrative user. Otherwise, request
                                   for adding this item will be sent to the initial administrative user.

                                2. If you click the "My Items" button, the My Items Menu page will be shown to you.

                                3. If you click the "Main menu" button, the Main Menu page will be shown to you.

                        2. If you click the "Add To Wishlist" button, the Add To Wishlist page will be shown to you.
                           On this page, the names of all items in the general inventory will be shown and you can
                           click an item name to add the item to your wishlist, and also, buttons called "My Items"
                           and "Main menu" are shown.
                           Note: The general inventory is a list of item that is initially empty.

                           Clicking an item name adds the item to their wishlist.
                           The "My Items" button leads to the My Items Menu page.
                           The "Main menu" button leads to the main menu page for administrative users.

                                1. If you click one item name, the item will be added to your wishlist.

                                2. If you click the "My Items" button, the My Items Menu page will be shown to you.

                                3. If you click the "Main menu" button, the Main Menu page will be shown to you.

                        3. If you click the "My Items" button, the My Items Menu page will be shown to you.

                        4. If you click the "Main menu" button, the Main Menu page will be shown to you.

                5. If you click the "Remove Item" button, the Remove Item page will be shown to you.
                   On this page, users can enter the name of the item they want to remove from their inventory
                   or wishlist in the box after the text "The name of item you want to remove" then click the
                   "remove" button to first select the item they want to remove, and also, buttons called
                   "My Items" and "Main menu" are shown.

                   After entering the name of the item user want to remove, clicking the "remove" button leads
                   to the Confirm Removing Item page where user can confirm removing an item.
                   The "My Items" button leads to the My Items Menu page.
                   The "Main menu" button leads to the main menu page for administrative users.

                        1. If you click the "remove" button after entering the name of item you want to enter,
                           the Confirm Removing Item page will be shown.
                           On this page, the information of the item you want to remove and buttons called
                           "confirm remove", "My Items", and "Main menu" will be shown.

                           The "confirm remove" button removes the item you want to remove if you have it in
                           your inventory or wishlist.
                           The "My Items" button leads to the My Items Menu page.
                           The "Main menu" button leads to the main menu page for administrative users.

                                1. If you click the "confirm remove" button, the item you want to remove will
                                   be removed if you have it in your inventory or wishlist.

                                2. If you click the "My Items" button, the My Items Menu page will be shown to you.

                                3. If you click the "Main menu" button, the Main Menu page will be shown to you.

                        2. If you click the "My Items" button, the My Items Menu page will be shown to you.

                        3. If you click the "Main menu" button, the Main Menu page will be shown to you.

                6. If you click the "Wish List" button, the Wish List page will be shown to you.
                   On this page, the name of all items in you wish to borrow list (wishlist) will be shown.

                7. If you click the "Main menu" button, the Main Menu page will be shown to you.

        5. If you click the "My Account" button, the My Account Menu page will be shown to you.
           On this page, user's user id, account status, and home city are shown, and also, buttons called
           "Change password", "Set Vacation", and "Main Menu" are shown.

           The "Change password" button leads to the Change password page where user can change password.
           The "Set Vacation" button leads to the Select Vacation Starting and Ending Date page where user can
           set vacation starting and ending date before leaving for vacation.
           Note: When a user is on vacation, he/her is NOT able to trade.
           The "Main menu" button leads to the main menu page for administrative users.

                1. If you click the "Change password" button, the Change password page will be shown to you.
                   On this page, users can change their passwords by entering their old passwords in the box
                   after the text "Old password: " then entering their new passwords in the box after the text
                   "New password: " then entering their new passwords again in the box after the text
                   "Confirm new password: " then clicking the "complete" button, and also, button called "My Account"
                   is shown.

                   After filling all three boxes, clicking the "complete" button changes user's password.
                   Note: User's password won't be changed if the old password is entered wrong, or new passwords
                         entered don't match, or new password is the same as the old password.
                   The "My Account" button leads to the My Account Menu page.

                        1. If you click the "complete" button after filling all three boxes, your password will
                           be changed to the new password you entered.

                        2. If you click the "My Account" button, the My Account Menu page will be shown to you.

                2. If you click the "Set Vacation" button, the Select Vacation Starting and Ending Date page will
                   be shown to you.
                   On this page, users can set their vacation starting and ending date before leaving for vacation
                   by choosing the starting and ending date using the two buttons with "..." then clicking the
                   "complete" button, and also, button called "My Account" is shown. Choosing the upper button
                   with "..." sets the starting date of user's vacation, and choosing the lower button with "..."
                   sets the ending date of user's vacation.

                   Clicking the "complete" button after choosing starting and ending date confirms the date settings.
                   The "My Account" button leads to the My Account Menu page.

                        1. If you click the "complete" button after choosing starting and ending date, your
                           starting and ending date of your vacation will be set.

                        2. If you click the "My Account" button, the My Account Menu page will be shown to you.

                3. If you click the "Main menu" button, the Main Menu page will be shown to you.

        6. If you click the "Manage User" button, the Manage User Menu page will be shown to you.
           On this page, buttons called "Change user's threshold", "Upgrade normal user to admin user",
           "AdminUser Menu", and "Main menu" are shown.

           The "Change user's threshold" button leads to the Change user's threshold page where administrative
           user(s) can change user's threshold for trading.
           The "Upgrade normal user to admin user" button leads to the Upgrade normal user to admin user page
           where administrative user(s) can upgrade normal user(s) to administrative user(s).
           The "AdminUser Menu" button leads to the AdminUser Menu page which contain operations can be done
           by administrative user.
           The "Main menu" button leads to the main menu page for administrative users.

                1. If you click the "Change user's threshold" button, the Change user's threshold page will be
                   shown to you.
                   On this page, admin user can change one user's threshold by entering the id of the user whose
                   threshold admin user wants to change in the box after text "User id: " then entering the new
                   threshold in the box after the text "Threshold: " then clicking the "Confirm" button, and also,
                   buttons called "display all normal users id", "Manage User", and "Main menu" are shown.

                   Clicking the "Confirm" button after filling the two boxes changes user's threshold.
                   The "display all normal users id" button shows all normal users' id.
                   The "Manage User" button leads to the Manage User Menu page.
                   The "Main menu" button leads to the main menu page for administrative users.

                        1. If you click the "Confirm" button after filling the two boxes, the threshold of the user
                        whose id is filled in the first box will be changed to the integer filled in the second box.

                        2. If you click the "display all normal users id" button, all normal users' id will
                           be shown to you.

                        3. If you click the "Manage User" button, the Manage User Menu page will be shown to you.

                        4. If you click the "Main menu" button, the Main Menu page will be shown to you.

                2. If you click the "Upgrade normal user to admin user" button, the Upgrade normal user to admin user
                   page will be shown to you.
                   On this page, admin user can upgrade one normal user to admin user by entering the normal user's
                   id in the box after the text "User id: " then clicking the "Confirm" button, and also, buttons
                   called "display all normal users id", "Manage User", and "Main menu" are shown.

                   Clicking the "Confirm" button after filling the box upgrades normal user to admin user.
                   The "display all normal users id" button shows all normal users' id.
                   The "Manage User" button leads to the Manage User Menu page.
                   The "Main menu" button leads to the main menu page for administrative users.

                        1. If you click the "Confirm" button after filling the box, the normal user whose id is
                           filled in the box will be upgraded to admin user.

                        2. If you click the "display all normal users id" button, all normal users' id will
                           be shown to you.

                        3. If you click the "Manage User" button, the Manage User Menu page will be shown to you.

                        4. If you click the "Main menu" button, the Main Menu page will be shown to you.

                3. If you click the "AdminUser Menu" button, the AdminUser Menu page will be shown.
                   On this page, buttons called "Freeze User", "Adjust all users' threshold",
                   "The requests from users", "Cancelling User Actions", and "Main menu" are shown.

                   The "Freeze User" button leads to the Freeze User page where admin user can freeze user.
                   The "Adjust all users' threshold" button changes all users' threshold.
                   The "The requests from users" button leads to Requested New Items page where requests are shown.
                   The "Cancelling User Actions" button leads to Cancelling User Actions page where admin user
                   can cancel normal users' actions.
                   The "Main menu" button leads to the main menu page for administrative users.

                        1. If you click the "Freeze User" button, the Freeze User page will be shown to you.
                           On this page, buttons called "All frozen users" and "Back to requests page" are shown.

                           The "All frozen users" button shows all users requested to be frozen and admin user can
                           right click the user to choose whether to freeze them or not.
                           The "Back to requests page" leads to the AdminUser Menu page.

                                1. If you click the "All frozen users" button, all users requested to be frozen
                                   will be shown, and for every user shown, you can choose whether to freeze this
                                   user or not.

                                2. If you click the "Back to requests page" button, the AdminUser Menu page will
                                   be shown to you.

                        2. If you click the "Adjust all users' threshold" button, you can adjust all users' threshold.
                           User's threshold will be changed to 0.3* the size of wishToBorrow list for this user.

                        3. If you click the "The requests from users" button, the Requested New Items page will be
                           shown to you.
                           On this page, buttons called "Request for Adding Item", "Unfreezing Account Request", and
                           "Main Menu" are shown.

                           The "Request for Adding Item" button leads to the Request for Adding Item page where admin
                           user can check the requests for adding item.
                           The "Unfreezing Account Request" button leads to the Unfreezing Account Request page where
                           admin user can check the unfreezing requests.
                           The "Main Menu" button leads to the AdminUser Menu page.

                                1. If you click the "Request for Adding Item" button, the Request for Adding Item page
                                   will be shown to you.
                                   On this page, buttons called "Request for Adding Item page" and
                                   "Back to requests page" are shown.

                                   Clicking the "Request for Adding Item" button shows all requests for adding
                                   item.
                                   The "Back to requests page" buttons leads to the Requested New Items page.

                                        1. If you click the "Request for Adding Item" button, all requests for
                                           adding item will be shown to you, and for every request shown, you can
                                           right click the request to choose whether to confirm this request or not.

                                        2. If you click the "Back to requests page" button, the Requested New Item
                                           page will be shown to you.

                                2. If you click the "Unfreezing Account Request" button, the Unfreezing Account Request
                                   page will be shown to you.
                                   On this page, buttons called "Unfreezing Account Request" and
                                   "Back to requests page" are shown.

                                   Clicking the "Unfreezing Account Request" button shows all requests for unfreezing.
                                   The "Back to requests page" buttons leads to the Requested New Items page.

                                        1. If you click the "Unfreezing Account Request" button, all requests for
                                           unfreezing will be shown to you, and for every request shown, you can
                                           right click the user to choose whether to confirm this request or not.

                                        2. If you click the "Back to requests page" button, the Requested New Item
                                           page will be shown to you.

                                3. If you click the "Main Menu" button, the AdminUser Menu page will be shown to you.

                        4. If you click the "Cancelling User Actions" button, the Canceling User Actions page
                           will be shown.
                           On this page, buttons called "All users" and "Main Menu" are shown.

                           Clicking the "All users" button shows the information for all user(s).
                           The "Main Menu" button leads to the AdminUser Menu page.

                                1. If you click the "All users" button, the information of all users will be shown.
                                   By right clicking the information line, you can cancel the action(s) of the user
                                   whose information line is right clicked.

                                2. If you click the "Main Menu" button, the AdminUser Menu page will be shown to you.

                        5. If you click the "Main menu" button, the Main Menu page will be shown to you.

                4. If you click the "Main menu" button, the Main Menu page will be shown to you.

        7. If you click the "Log Out" button, the login and register page will be shown to you again.


2. For normal users:

        After clicking the "normal login" button, the main menu for normal user will be shown to you.
        Note: the difference between the main menu for admin user and the main menu for normal user is that
              the main menu for normal user doesn't contain the "Manage User" button, but the main menu for
              admin user contains.


3. For guest users:

        After clicking the "guest login" button, the main menu for guest user will be shown to you.
        Note: On the main menu for guest user, there are only "My Items" button, "My Account" button,
              and "Log Out" button.

              The "My Items" button leads to My Items Menu page for guest user which only contains
              "Add Item" button, "Remove Item" button, "Wish List" button, and "Main menu" button.
              The first three buttons work the same as in the main menu for admin user and normal user.
              The "Main menu" button leads to the main menu for guest user.

              The "My Account" button leads to My Account page for guest user which only contains one label
              showing a temporary user id, one label showing account status: Guest, "Main menu" button,
              one label telling "For more operations please sign in", and "register" button.
              The "Main menu" button leads to the main menu for guest user.
              The "register" button works the same as in the login and register page for admin user and
              normal user.

              The "Log Out" button works the same as in the main menu for admin user and normal user.

