package com.jazart.buttonchallenge;
import android.content.res.Resources;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;


import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/*
Main entry to the program. Here I assign and set up views/widgets and set up my retrofit network and cache.
II use a recyclerviw to store the returned response list.
 */
public class MainActivity extends AppCompatActivity implements NewUserDialog.EditUserDialogListener, NewTransferDialog.EditTransferDialogListener {
    public static final String TAG = "Main Activity";
    public static final String DIALOG_NEW_USER = "Dialog New User";
    public static final String NEW_TRANSFER_DIALOG = "New Transfer Dialog";
    public static Resources sResources;
    private com.github.clans.fab.FloatingActionButton createUserButton, createTransferButton;
    private List users;
    private UserAdapter mUserAdapter;
    private RecyclerView mRecyclerView;
    private ProgressBar mProgressBar;
    private Network mNetwork;
    private FakeButtonService mButtonService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //static var to get resources in classes where Context in not availible
        sResources = getResources();

        mProgressBar = findViewById(R.id.progress);
        mProgressBar.setVisibility(View.VISIBLE);

        createUserButton = findViewById(R.id.new_user);

        //setting up listeners for click events and displaying appropriate dialogs.

        createUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NewUserDialog dialog = new NewUserDialog();
                FragmentManager fm = getSupportFragmentManager();
                dialog.show(fm, DIALOG_NEW_USER);
            }
        });

        createTransferButton = findViewById(R.id.new_transfer);
        createTransferButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NewTransferDialog transferDialog = new NewTransferDialog();
                transferDialog.show(getSupportFragmentManager(), NEW_TRANSFER_DIALOG);

            }
        });

        //final ListView listView = findViewById(R.id.lv);

        mNetwork = new Network(getCacheDir());
        mButtonService = mNetwork.getButtonService();

        mRecyclerView = findViewById(R.id.rv);
        mUserAdapter = new UserAdapter();


        listUsers();




    }

    //Creating a new transfer post. A successful transfer will give a success toast while failure will give fail toast and error msg.
    private void newTransfer(Transfer transfer) {
        Call<Transfer> call = mButtonService.newTransfer(transfer);

        call.enqueue(new Callback<Transfer>() {
            @Override
            public void onResponse(Call<Transfer> call, Response<Transfer> response) {
                if(response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Transfer Successful!", Toast.LENGTH_SHORT)
                            .show();
                    Log.e(TAG, "Transfer Successful" + response.raw());
                } else {
                    Toast.makeText(MainActivity.this, "Transfer failed "+ response.message(), Toast.LENGTH_LONG)
                            .show();
                }
            }

            @Override
            public void onFailure(Call<Transfer> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Transfer failed "+ t.getMessage(), Toast.LENGTH_LONG)
                        .show();
                Log.e(TAG, "Transfer Failed");
                t.printStackTrace();
            }
        });
    }

    //post new user to the assigned candidate.
    public void addUser(User user) {
        Call<User> call = mButtonService.insertUser(user);
        call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    mProgressBar.setVisibility(View.INVISIBLE);
                    Log.e(TAG, "Success");
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Log.d(TAG, "Fail");
                }
            });
    }

    //method for to pass in information from dialog interface. This allows me to display dialog and then get object back once it's been dismissed
    @Override
    public void onFinishedEditDialog(User user) {
        addUser(user);
        listUsers();
    }

    //list all users for the particular candidate. Hardcoded in a single candidate. Instructions wasn't specific for more than one.
    public void listUsers() {
        mButtonService.listUsers("Josh").enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {

                if (response.isSuccessful()) {
                    mProgressBar.setVisibility(View.INVISIBLE);
                    Log.e(TAG, response.toString());

                    users = response.body();
                    //ArrayAdapter<User> userArrayAdapter = new ArrayAdapter<User>(MainActivity.this,
                    //     android.R.layout.simple_list_item_1,
                    //    users);

                    mUserAdapter.setList(users);
                    mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    mRecyclerView.setAdapter(mUserAdapter);
                }

            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }


    @Override
    public void onFinishedEditingTransfer(Transfer transfer) {
        newTransfer(transfer);
    }
}
