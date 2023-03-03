package com.ag.bta.ui.about.helper;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.FrameLayout;

import com.ag.bta.main.R;
import com.ag.bta.ui.about.AboutBuilder;
import com.ag.bta.ui.about.views.AboutView;

public class AdminAboutHelper implements View.OnClickListener {

   private Activity activity;
  // public static int theme =  R.style.Theme_AppCompat_NoActionBar;

   private AdminAboutHelper(Activity activity) {
       this.activity = activity;
   }

   public static AdminAboutHelper with(Activity activity) {
       return new AdminAboutHelper(activity);
   }

   public AdminAboutHelper init() {

      /* activity.findViewById(R.id.dark).setOnClickListener(this);
       activity.findViewById(R.id.light).setOnClickListener(this);
       activity.findViewById(R.id.custom).setOnClickListener(this);
*/
       return this;
   }

   public void loadAbout() {
       final FrameLayout flHolder = (FrameLayout)activity.findViewById(R.id.about);

       AboutBuilder builder = AboutBuilder.with(activity)
               .setAppIcon(R.drawable.logo)
               .setAppName(R.string.app_name)
               .setPhoto(R.drawable.about_profile_picture)
              // .setCover(R.drawable.vtpowerbanner)
               .setLinksAnimated(true)
               //.setDividerDashGap(13)
               .setName("Tamilarasan")
               .setSubTitle("Proprietor VT Power Solution")
               .setLinksColumnsCount(3)
               .setBrief("Ultimate Destination to buy home Inverter & Battery")



               .addGooglePlusLink("+JuniorVansuita")

               .addGoogleLink("user")
                .addFiveStarsAction()
                .setVersionNameAsAppSubTitle()
               .addShareAction(R.string.app_name)
               .addUpdateAction()
               .setActionsColumnsCount(2)
               .addFeedbackAction("vtpower.sln@gmail.com")
               .addPrivacyPolicyAction("http://www.docracy.com/2d0kis6uc2")
               .addIntroduceAction((Intent) null)
               .addHelpAction((Intent) null)
/*                .addChangeLogAction((Intent) null)
*/               // .addRemoveAdsAction((Intent) null)
               .addDonateAction((Intent) null)
               .setWrapScrollView(true)
               .setShowAsCard(true);

       AboutView view = builder.build("Admin");

       flHolder.addView(view);
   }

   public void Backupload() {
       final FrameLayout flHolder = (FrameLayout)activity.findViewById(R.id.about);

       AboutBuilder builder = AboutBuilder.with(activity)
               .setAppIcon(R.drawable.logo)
               .setAppName(R.string.app_name)
               .setPhoto(R.drawable.about_profile_picture)
               .setCover(R.drawable.about_profile_cover)
               .setLinksAnimated(true)
               .setDividerDashGap(13)
               .setName("Tamilarasan")
               .setSubTitle("Proprietor Vt Power Solution")
               .setLinksColumnsCount(3)
               .setBrief("I'm warmed of mobile technologies. Ideas maker, curious and nature lover.")

               .addBitbucketLink("jrvansuita")



               .addGooglePlusLink("+JuniorVansuita")






               .addGoogleLink("user")


               .addFiveStarsAction()

               .setVersionNameAsAppSubTitle()
               .addShareAction(R.string.app_name)
               .addUpdateAction()
               .setActionsColumnsCount(2)
               .addFeedbackAction("vtpower.sln@gmail.com")
               .addPrivacyPolicyAction("http://www.docracy.com/2d0kis6uc2")
               .addIntroduceAction((Intent) null)
               .addHelpAction((Intent) null)

               .addRemoveAdsAction((Intent) null)
               .addDonateAction((Intent) null)
               .setWrapScrollView(true)
               .setShowAsCard(true);

       AboutView view = builder.build("Admin");

       flHolder.addView(view);
   }


   @Override
   public void onClick(View view) {
      /* switch (view.getId()) {
           case R.id.dark:
               if (theme != R.style.AppThemeDark) {
                   theme = R.style.AppThemeDark;
                   activity.recreate();
               }
               break;
           case R.id.light:
               if (theme != R.style.AppThemeLight) {
                   theme = R.style.AppThemeLight;
                   activity.recreate();
               }
               break;

           case R.id.custom:
               if (theme != R.style.AppThemeCustom) {
                   theme = R.style.AppThemeCustom;
                   activity.recreate();
               }
               break;

           default:
               break;
       }*/
  }
}
