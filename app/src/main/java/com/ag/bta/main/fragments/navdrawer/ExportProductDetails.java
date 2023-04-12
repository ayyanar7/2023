package com.ag.bta.main.fragments.navdrawer;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Process;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ShareCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ag.bta.main.R;
import com.ag.bta.utils.excel.common.Constants;
import com.ag.bta.utils.excel.contract.IMainActivityContract;
import com.ag.bta.utils.excel.data.ContactResponse;
import com.ag.bta.utils.excel.data.response.BooleanResponse;
import com.ag.bta.utils.excel.data.response.DataResponse;
import com.ag.bta.utils.excel.data.response.StateDefinition;
import com.ag.bta.utils.excel.view.adapter.ContactsAdapter;
import com.ag.bta.utils.excel.viewModel.MainActivityViewModel;
import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;


public class ExportProductDetails extends Fragment implements IMainActivityContract.View {
    private static final String TAG = ExportProductDetails.class.getSimpleName();


    private MainActivityViewModel mViewModel;

    private HandlerThread importContactsHandlerThread;
    private Handler contactsHandler;
    private HandlerThread generateExcelHandlerThread;
    private Handler excelHandler;
    private HandlerThread readExcelDataHandlerThread;
    private Handler readExcelHandler;

    private Button importContactsButton;
    private Button exportExcelButton;
    private Button readExcelButton;
    private FloatingActionButton shareButton;
    private RecyclerView contactsRecyclerView;
    private ConstraintLayout constraintLayout;
    private LottieAnimationView lottieAnimationView;
    private LottieAnimationView importLottieView;
    private LottieAnimationView exportLottieView;
    private LottieAnimationView readLottieView;

    private final String NO_DATA_ANIMATION = "no_data.json";
    private final String LOADING_ANIMATION = "loading.json";
    private final String ERROR_ANIMATION = "error.json";
    private final String DONE_ANIMATION = "done.json";
    private final String CANCEL_ANIMATION = "cancel.json";

    private final String[] PERMISSIONS = {
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    private List<ContactResponse> contactsList;
    private List<ContactResponse> importedExcelContactsList;

    /**
     * Observer for getContactsFromCPLiveData
     */
    private final Observer<DataResponse<ContactResponse>> importContactsFromCPObserver = contactResponse ->  {
        Log.e(TAG, "importContactsFromCPObserver onChanged()");

        if (contactResponse.getState() == StateDefinition.State.SUCCESS) {
            setupLottieAnimation(lottieAnimationView, NO_DATA_ANIMATION);

            if (contactResponse.getData().size() > 0) {
                contactsList.clear();
                contactsList.addAll(contactResponse.getData());
                displaySnackBar("Retrieved "+contactsList.size()+" contacts from device.");

                // Disable Import button
                disableUIComponent(importContactsButton);
                setupLottieAnimation(importLottieView, DONE_ANIMATION);

                // Enable Export button and set onClickListener
                enableUIComponent(exportExcelButton);
                exportExcelButton.setOnClickListener(view -> onExportIntoExcelButtonClicked());

            } else {
                displaySnackBar("No contacts found");
                setupLottieAnimation(lottieAnimationView, ERROR_ANIMATION);
            }

        } else if (contactResponse.getState() == StateDefinition.State.ERROR) {
            setupLottieAnimation(lottieAnimationView, ERROR_ANIMATION);

            String errorMessage = (contactResponse.getErrorData().getErrorStatus()
                    + contactResponse.getErrorData().getErrorMessage());

            setupLottieAnimation(importLottieView, CANCEL_ANIMATION);
            displaySnackBar(errorMessage);
        } else {
            setupLottieAnimation(lottieAnimationView, LOADING_ANIMATION);
        }
    };

    /**
     * Observer for isExcelGeneratedLiveData
     */
    private final Observer<BooleanResponse> excelGenerationObserver = booleanResponse -> {
        Log.e(TAG, "excelGenerationObserver onChanged()");

        if (booleanResponse.getState() == StateDefinition.State.SUCCESS) {
            setupLottieAnimation(lottieAnimationView, NO_DATA_ANIMATION);
            displaySnackBar(Constants.EXCEL_FILE_NAME+" generated Successfully");

            // Disable Export button
            disableUIComponent(exportExcelButton);
            setupLottieAnimation(exportLottieView, DONE_ANIMATION);

            // Enable Read button and set onClickListener
            enableUIComponent(readExcelButton);
            readExcelButton.setOnClickListener(view -> onReadFromExcelButtonClicked());

        } else if (booleanResponse.getState() == StateDefinition.State.ERROR) {
            setupLottieAnimation(lottieAnimationView, ERROR_ANIMATION);

            String errorMessage = (booleanResponse.getErrorData().getErrorStatus()
                    + booleanResponse.getErrorData().getErrorMessage());

            setupLottieAnimation(exportLottieView, CANCEL_ANIMATION);
            displaySnackBar(errorMessage);
        } else {
            setupLottieAnimation(lottieAnimationView, LOADING_ANIMATION);
        }
    };

    /**
     * Observer for readContactsFromExcelLiveData
     */
    private final Observer<DataResponse<ContactResponse>> readExcelDataObserver = dataResponse -> {
        Log.e(TAG, "readExcelDataObserver onChanged()");

        if (dataResponse.getState() == StateDefinition.State.SUCCESS) {

            if (dataResponse.getData().size() > 0) {
                importedExcelContactsList.clear();
                importedExcelContactsList.addAll(dataResponse.getData());
                displaySnackBar("Fetched "+importedExcelContactsList.size()+" contacts from Excel.");

                // Disable Read button
                disableUIComponent(readExcelButton);
                setupLottieAnimation(readLottieView, DONE_ANIMATION);

                setupRecyclerView();
            } else {
                displaySnackBar("No contacts found");
                setupLottieAnimation(lottieAnimationView, ERROR_ANIMATION);
            }

        } else if (dataResponse.getState() == StateDefinition.State.ERROR) {
            setupLottieAnimation(lottieAnimationView, ERROR_ANIMATION);

            String errorMessage = (dataResponse.getErrorData().getErrorStatus()
                    + dataResponse.getErrorData().getErrorMessage());

            setupLottieAnimation(readLottieView, CANCEL_ANIMATION);
            displaySnackBar(errorMessage);
        } else {
            setupLottieAnimation(lottieAnimationView, LOADING_ANIMATION);
        }
    };


    /**
     * Importing contacts Runnable to parse data in a Background HandlerThread
     */
    private final Runnable importContactsRunnable = new Runnable() {
        @Override
        public void run() {
            Log.e(TAG, "importContactsRunnable run: ");
            mViewModel.initiateImport();
        }
    };

    /**
     * Generate Excel Runnable
     */
    private final Runnable generateExcelRunnable = new Runnable() {
        @Override
        public void run() {
            Log.e(TAG, "generateExcelRunnable run: ");
            mViewModel.initiateExport(contactsList);
        }
    };

    /**
     * Read Excel data runnable
     */
    private final Runnable readExcelDataRunnable = new Runnable() {
        @Override
        public void run() {
            Log.e(TAG, "readExcelDataRunnable run: ");
            mViewModel.initiateRead();
        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // get title
        //  title = getArguments().getString("title");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.excel_activity_main, null);


        return view;
    }
View v = null;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        contactsList = new ArrayList<>();
        importedExcelContactsList = new ArrayList<>();

        mViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        mViewModel.getContactsFromCPLiveData().observe(getViewLifecycleOwner(), importContactsFromCPObserver);
        mViewModel.isExcelGeneratedLiveData().observe(getViewLifecycleOwner(), excelGenerationObserver);
        mViewModel.readContactsFromExcelLiveData().observe(getViewLifecycleOwner(), readExcelDataObserver);
       this.v= view;// not to remove
        initializeViews();
        setupHandlerThreads();
    }

    @Override
    public void onStart() {
        super.onStart();
        requestPermissions();
    }

    @Override
    public void onResume() {
        super.onResume();

        boolean isPermissionGranted = checkPermissionsAtRuntime();

        if (isPermissionGranted) {
            importContactsButton.setOnClickListener(view -> onImportContactButtonClicked());
        }

        shareButton.setOnClickListener(view -> onShareButtonClicked());
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        destroyHandlerThreads();

        mViewModel.getContactsFromCPLiveData().removeObservers(this);
        mViewModel.isExcelGeneratedLiveData().removeObservers(this);
    }

    @Override
    public void initializeViews( ) {
        Log.e(TAG, "initializeViews: ");

       importContactsButton = (Button) v.findViewById(R.id.import_contact_button)  ;
        exportExcelButton = (Button) v.findViewById(R.id.export_contact_button)  ;
        readExcelButton = (Button) v.findViewById(R.id.read_excel_data_button)  ;
        shareButton = (FloatingActionButton) v.findViewById(R.id.share_excel_floating_button)  ;
        contactsRecyclerView = (RecyclerView) v.findViewById(R.id.display_contacts_recycler_view) ; //
        constraintLayout =  (ConstraintLayout) v.findViewById(R.id.constraint_layout);
        lottieAnimationView = (LottieAnimationView) v.findViewById(R.id.lottie_animation_view);
        importLottieView = (LottieAnimationView) v.findViewById(R.id.import_contact_lottie);
        exportLottieView = (LottieAnimationView) v.findViewById(R.id.export_contact_lottie);
        readLottieView = (LottieAnimationView) v.findViewById(R.id.read_contact_lottie);

        disableUIComponent(exportExcelButton);
        disableUIComponent(readExcelButton);

        setupLottieAnimation(lottieAnimationView, NO_DATA_ANIMATION);
    }

    @Override
    public void setupLottieAnimation(LottieAnimationView lottieView, String animationName) {
        if (lottieView.isAnimating()) {
            lottieView.cancelAnimation();
        }
        lottieView.setAnimation(animationName);
        lottieView.playAnimation();
    }

    @Override
    public void setupHandlerThreads() {
        Log.e(TAG, "setupHandlerThreads: ");

        // Import Contacts handler thread
        importContactsHandlerThread = new HandlerThread("ImportContactsThread",
                Process.THREAD_PRIORITY_BACKGROUND);
        importContactsHandlerThread.start();
        contactsHandler = new Handler(importContactsHandlerThread.getLooper());

        // Generate Excel handler thread
        generateExcelHandlerThread = new HandlerThread("GenerateExcelThread",
                Process.THREAD_PRIORITY_BACKGROUND);
        generateExcelHandlerThread.start();
        excelHandler = new Handler(generateExcelHandlerThread.getLooper());

        // Read Excel handler thread
        readExcelDataHandlerThread = new HandlerThread("ReadExcelHandlerThread",
                Process.THREAD_PRIORITY_BACKGROUND);
        readExcelDataHandlerThread.start();
        readExcelHandler = new Handler(readExcelDataHandlerThread.getLooper());
    }

    @Override
    public void destroyHandlerThreads() {
        Log.e(TAG, "destroyHandlerThreads: ");
        importContactsHandlerThread.quitSafely();
        generateExcelHandlerThread.quitSafely();
        readExcelDataHandlerThread.quitSafely();
    }

    @Override
    public void onImportContactButtonClicked() {
        Log.e(TAG, "onImportContactButtonClicked: ");
        contactsHandler.post(importContactsRunnable);
    }

    @Override
    public void onExportIntoExcelButtonClicked() {
        Log.e(TAG, "onExportIntoExcelButtonClicked: ");
        excelHandler.post(generateExcelRunnable);
    }

    @Override
    public void onReadFromExcelButtonClicked() {
        Log.e(TAG, "onReadFromExcelButtonClicked: ");
        readExcelHandler.post(readExcelDataRunnable);
    }

    @Override
    public void onShareButtonClicked() {
        Log.e(TAG, "onShareButtonClicked: ");
        Uri fileUri = mViewModel.initiateSharing();

        if (fileUri == null) {
            displaySnackBar("Generate Excel before sharing");
        } else {
            launchShareFileIntent(fileUri);
        }
    }

    @Override
    public void switchVisibility(View view, int visibility) {
        view.setVisibility(visibility);
    }

    @Override
    public void enableUIComponent(View componentName) {
        componentName.setClickable(true);
        componentName.setAlpha(1);
    }

    @Override
    public void disableUIComponent(View componentName) {
        componentName.setClickable(false);
        componentName.setAlpha((float) 0.4);
    }

    @Override
    public void setupRecyclerView() {
        Log.e(TAG, "setupRecyclerView: ");

        switchVisibility(lottieAnimationView, View.GONE);
        switchVisibility(contactsRecyclerView, View.VISIBLE);

        ContactsAdapter mAdapter = new ContactsAdapter(importedExcelContactsList);
        contactsRecyclerView.setHasFixedSize(true);
        contactsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        contactsRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void displaySnackBar(String message) {
        Snackbar.make(constraintLayout, message, BaseTransientBottomBar.LENGTH_SHORT)
                .show();
    }

    @Override
    public boolean checkPermissionsAtRuntime() {
        Log.e(TAG, "checkPermissionsAtRuntime: ");
        for (String permission : PERMISSIONS) {
            if (ActivityCompat.checkSelfPermission(getContext(), permission)
                    != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void requestPermissions() {
        Log.e(TAG, "requestPermissions: ");
        ActivityCompat.requestPermissions(getActivity(), PERMISSIONS, Constants.REQUEST_PERMISSION_ALL);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        boolean isAlertDialogInflated = false;
        boolean isUIDisabled = false;

        if (requestCode == Constants.REQUEST_PERMISSION_ALL) {
            for (int i = 0; i < permissions.length; i++) {
                String permission = permissions[i];
                if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                    boolean showRationale = ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), permission);
                    if (!showRationale) {
                        // Called when user selects 'NEVER ASK AGAIN'
                        isAlertDialogInflated = true;

                    } else {
                        // Called when user selects 'DENY'
                        displaySnackBar("Enable all permissions");
                        isUIDisabled = true;

                        disableUIComponent(importContactsButton);
                        disableUIComponent(exportExcelButton);
                        disableUIComponent(readExcelButton);
                    }
                } else if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    // Called when user selects 'ALLOW'
                    if (!isUIDisabled) {
                        enableUIComponent(importContactsButton);
                    }

                }
            }

            inflateAlertDialog(isAlertDialogInflated);
        }

    }

    /**
     * Method: Show Alert Dialog when User denies permission permanently
     */
    private void inflateAlertDialog(boolean isTrue) {
        if (isTrue) {
            // Inflate Alert Dialog
            new MaterialAlertDialogBuilder(getContext())
                    .setTitle("Permissions Mandatory")
                    .setMessage("Kindly enable all permissions through Settings")
                    .setPositiveButton("OKAY", (dialogInterface, i) -> {
                        launchAppSettings();
                        dialogInterface.dismiss();
                    })
                    .setCancelable(false)
                    .show();
        }
    }

    /**
     * Method: Launch App-Settings Screen
     */
    private void launchAppSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getContext().getPackageName(), null);
        intent.setData(uri);
        startActivityForResult(intent, Constants.REQUEST_PERMISSION_SETTING);
    }

    /**
     * Method: Launch Share file screen
     */
    private void launchShareFileIntent(Uri uri) {
        Intent intent = ShareCompat.IntentBuilder.from(getActivity())
                .setType("application/pdf")
                .setStream(uri)
                .setChooserTitle("Select application to share file")
                .createChooserIntent()
                .addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        startActivity(intent);
    }
}