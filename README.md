# schedule-builder

this API won`t work without the Constant.java file, for securty reasons I decide no to track the Constant file with git.
to make it function you will have to create your own Constant Class and create your own Secret key for JWT authentications porpuses.
the Constant class should look something like:
//* CODE * //

public class Constant {
    public static final int BASE_TIME_CARD_ID = 100;
    public static final double MINIMUM_WAGE = 14.13; // new Jersey minimum as per 2023

    // *SECURITY CONSTATS BELOW */

    public static final String SECRET_KEY = ** CREATE YOUR OWN ***

    public static final int TOEKN_TIME_EXIRATION = *** Decide your own expiration time *** ;
    public static final String AUTHORIZATION = "Authorization";
    public static final String BEARER = "Bearer ";

}
