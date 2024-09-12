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
