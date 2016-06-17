package seng1.rockpapertoe;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

    /*
     * Activity to sign in the user with his google account (mail address and password)
     * @author JuliusSchengber
     */
public class SignInActivity extends AppCompatActivity implements
        GoogleApiClient.OnConnectionFailedListener,
        View.OnClickListener {

    private static final String TAG = "SignInActivity";
    private static final int signinCode = 1;

    private GoogleApiClient apiClient;
    private TextView statusTextView;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        // Views
        statusTextView = (TextView) findViewById(R.id.status);
        /*
        *setup Button listeners
        * @author JuliusSchengber
        */

        findViewById(R.id.sign_in_button).setOnClickListener(this);
        findViewById(R.id.sign_out_button).setOnClickListener(this);
        findViewById(R.id.continue_button).setOnClickListener(this);

       /*
        * specify information to request from the user
        * ID and basic profile are included in Default_SIGN_IN
        * @author JuliusSchengber
        */
        GoogleSignInOptions ops = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestProfile()
                .requestEmail()
                .build();



        /*Build a GoogleApiClient with access to the Google Sign-In API and the
        *options specified
        * @author JuliusSchengber
        */
        apiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, ops)
                .build();


    }

    @Override
    public void onStart() {
        super.onStart();

        OptionalPendingResult<GoogleSignInResult> cachedSignIn = Auth.GoogleSignInApi.silentSignIn(apiClient);
        if (cachedSignIn.isDone()) {
            /*
            * if the user has signed in before and it´s cached and valid, these information are used
            * @author JuliusSchengber
             */
            Log.d(TAG, "Got cached sign-in");
            GoogleSignInResult result = cachedSignIn.get();
            handleSignInResult(result);

        } else {
            // If the user has not previously signed in on this device or the sign-in has expired,
            // this asynchronous branch will attempt to sign in the user silently.  Cross-device
            // single sign-on will occur in this branch.
            showProgressDialog();
            cachedSignIn.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(GoogleSignInResult googleSignInResult) {
                    hideProgressDialog();
                    handleSignInResult(googleSignInResult);
                }
            });
        }
    }

    /*
     *get Sign in Result
     * @author JuliusSchengber
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //result from GoogleSignInApi
        if (requestCode == signinCode) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    /*
    *handle the Sign In Result
    *@author JuliusSchengber
     */
    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            /*
            *Signed in successfully, get user information
            *and show user´s mail address
            * in statusTextView
            * @author JuliusSchengber
             */
            GoogleSignInAccount acct = result.getSignInAccount();
            String signinmail = acct.getEmail();
            if(signinmail != null) {
                statusTextView.setText(signinmail);
            }
            else{
                statusTextView.setText("Angemeldet");
            }
            //Signed in, show authenticated UI.
            updateUI(true);
        } else {
            // Signed out, show unauthenticated UI.
            updateUI(false);
        }
    }


    /*
    *Sign in the user
     */
    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(apiClient);
        startActivityForResult(signInIntent, signinCode);
    }

    /*
    * sign out the user
     */
    private void signOut() {
        Auth.GoogleSignInApi.signOut(apiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        updateUI(false);
                    }
                });
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        // An unresolvable error has occurred and Google APIs (including Sign-In) will not
        // be available.
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
    }

    private void showProgressDialog() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("loading...");
            progressDialog.setIndeterminate(true);
        }

        progressDialog.show();
    }

    private void hideProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.hide();
        }
    }

        /*
        * update the User Interface depending on the information
        * if the user is logged in or not
        * show and hide those buttons
        * @author JuliusSchengber
         */
    private void updateUI(boolean signedIn) {
        if (signedIn) {
            findViewById(R.id.sign_in_button).setVisibility(View.INVISIBLE);
            findViewById((R.id.sign_out_button)).setVisibility(View.VISIBLE);
            findViewById((R.id.continue_button)).setVisibility(View.VISIBLE);
        } else {
            statusTextView.setText(R.string.not_signed_in);
            findViewById(R.id.sign_in_button).setVisibility(View.VISIBLE);
            findViewById((R.id.sign_out_button)).setVisibility(View.INVISIBLE);
            findViewById((R.id.continue_button)).setVisibility(View.INVISIBLE);
        }
    }

        /*
        * go to StartScreen
        * @author JuliusSchengber
         */
    public void switchToStart(){
        startActivity(new Intent(this, StartActivity.class));
    }

        /*
        * handle the actions after buttons get clicked
        * @author JuliusSchengber
         */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_in_button:
                signIn();
                break;
            case R.id.sign_out_button:
                signOut();
                break;
            case R.id.continue_button:
                switchToStart();
                break;
        }
    }
}