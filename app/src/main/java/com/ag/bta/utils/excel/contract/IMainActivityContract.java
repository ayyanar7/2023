package com.ag.bta.utils.excel.contract;

import android.net.Uri;

import com.airbnb.lottie.LottieAnimationView;
import com.ag.bta.utils.excel.data.ContactResponse;

import java.util.List;


public interface IMainActivityContract {

    // View
    interface View {
        void initializeViews();
        void setupLottieAnimation(LottieAnimationView animationView, String animationName);
        void setupHandlerThreads();
        void destroyHandlerThreads();
        void onImportContactButtonClicked();
        void onExportIntoExcelButtonClicked();
        void onReadFromExcelButtonClicked();
        void onShareButtonClicked();
        void switchVisibility(android.view.View view, int visibility);
        void enableUIComponent(android.view.View componentName);
        void disableUIComponent(android.view.View componentName);
        void setupRecyclerView();
        void displaySnackBar(String message);
        boolean checkPermissionsAtRuntime();
        void requestPermissions();
    }

    // View-Model
    interface ViewModel {
        void initiateImport();
        void initiateExport(List<ContactResponse> dataList);
        void initiateRead();
        Uri initiateSharing();
    }
}
