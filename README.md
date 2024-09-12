* Task: Build a User Authentication API using JWT (Access & Refresh Tokens)


* Objective:

*   Create a REST API that allows users to sign up, log in, and log out using JWT for authentication and authorization. Implement Access and Refresh tokens for session management and build a secure API that retrieves user information once authenticated.


* Requirements:


     * Sign-up API: 
                Create a new user with a username, email, and password.


   *  Hash the password before saving it to the database.


* Login API:

   * Authenticate the user using their email and password.

   * Generate both Access and Refresh tokens after successful login.


    * Return these tokens in the response.


* Logout API:


     * Invalidate the refresh token (e.g., store invalid tokens in a blacklist or delete from database).


* Refresh Token API:


   *  Provide a route to refresh the Access token using the Refresh token.


   * Ensure that the Access token has a short expiry time (e.g., 15 minutes) and the Refresh token has a longer expiry (e.g., 7 days).


* User Info API (Protected API):


   * Implement a route that returns the authenticated user's information (e.g., username, email) when accessed with a valid Access token.


   * This API should return a 401 Unauthorized error if the Access token is expired or invalid.


* Constraints:

  * Use JWT for generating both Access and Refresh tokens.


   * Access Token: Include user information and set a short expiration time.


  * Refresh Token: Use to generate new Access tokens without requiring login and set a longer expiration time.


* Bonus:
   * Implement role-based access control (e.g., differentiate between regular users and admins).


* Technology Requirements:


  * Backend: Any backend framework (Spring Boot, Node.js, Express, etc.).


  * Database: Use PostgreSQL to store user credentials and tokens if needed.


* Note : I didn't implement role-based access control ( Long tutorial and no time :> )
  
* Note : The Info api won't work because of the cutomized detailService.

![Screenshot_20240912_215850](https://github.com/user-attachments/assets/6cbeb190-0a36-44a1-9733-387e999a9a17)

* Once authentication object was returnd back to the filter --> the filter will add the currently authenticated user to securtiy context ---> another word for the currently authenticated user is principle
----> now in order to access princple spring using a wrapper around security context
  which is called security context holder .
  
  ![Screenshot_20240912_220400](https://github.com/user-attachments/assets/936a7aa7-1900-45ec-8c8a-72d0687795e3)


* SecurityContextHolder ---> have a static get context method on it which we can call to retrieve a principle and because it's static we call it from any part of the call base.

* If we don't have a session we will be forced to go along all that to send a request .
  
![Screenshot_20240912_220626](https://github.com/user-attachments/assets/216eb60f-ee98-4bc4-8169-4f961add690f)

  * So there is a securityContextHolderFilter which will try to load our user from the session and if successded then the user doesn't have to re-authenticate .

  * Using JWT ----> statless form of authentication --> the data inside the token itself 


![Screenshot_20240912_215215](https://github.com/user-attachments/assets/abe859b8-cede-495e-a48d-2fd50db3fef8)

  ![Screenshot_20240912_215252](https://github.com/user-attachments/assets/d728541f-ecdd-4331-9205-437e61b9c4d0)

![Screenshot_20240912_215231](https://github.com/user-attachments/assets/90c87011-6b29-441a-8397-137dee59f10f)
