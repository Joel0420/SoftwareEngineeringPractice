# SoftwareEngineeringPractice
## grading

To Do | correct
---|---
at least 8 commits|
isEmailValid|
withdraw|
isamountValid|
constructor & withdraw fix|

Usecase: 
https://drive.google.com/file/d/16QeH0AdywvUHLzUHYaT50bWsE6ocJg3M/view?usp=sharing

UML:
https://drive.google.com/file/d/1ClJfvlx-DafIY19BRQ7PtKhMV5oUpCB4/view?usp=sharing

Sequence: 
https://drive.google.com/file/d/1Hd51PWDOxilkn22_zhSnwhkEnVxUM0fU/view?usp=sharing

https://drive.google.com/file/d/1QVD4o9ISDGrVABXhoTN2-LIzO8AcCr5z/view?usp=sharing

Manual Test Script: 
Scenario 2 (withdraw)
Correct sign in to account with at least $100 
Withdraw $75
Check bal = $25
Logout 

Scenario 1 (deposit)
Correct sign in to account with $0
Deposit $20 
Check bal = $20
Logout

Scenario 3 (deposit/withdraw)
Correct sign in to account with $0
Deposit $30  
Withdraw $25
Check bal = $5
Logout 

Scenario 4 (invalid transfer) 
Correct sign in to account with >$20 
Attempt to transfer $50 
Check that transfer does not go through 

Scenario 5 (invalid withdraw) 
Correct sign in to account with >$10
Attempt to withdraw $10
Check that withdraw did not happen
 	
Scenario 6 (invalid deposit)
Correct sign in 
Attempt to deposit $0
Check that deposit did not happen 

Scenario 7 (ghost login) 
Attempt to sign in with no credentials 
Check that signin didn't work 

Scenario 8 (invalid login) 
Attempt to sign in with random credentials 
Check that signin didnt work 

Scenario 9 (ghost transfer1)
Correct sign in to account with $20+
Attempt to transfer $0 
Check that transfer was blocked 

Scenario 10 (ghost transfer2)
Correct sign in to account with $20+
Attempt to transfer $10 to no specific account 
Check that transfer was blocked 

Scenario 11(ghost transfer3) 
Correct sign in to account with $20+
Attempt to transfer $10 without specifying account to 
Check that transfer was blocked 
