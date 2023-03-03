package com.ag.bta.ui.about;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.view.View.OnClickListener;
import androidx.annotation.NonNull;


import com.ag.bta.main.R;


import com.ag.bta.ui.about.utils.ColorUtil;
import com.ag.bta.ui.about.utils.IconUtil;
import com.ag.bta.ui.about.utils.IntentUtil;
import com.ag.bta.ui.about.views.AboutView;

import java.util.LinkedList;

@SuppressWarnings({"WeakerAccess", "unused"})
public final class AboutBuilder {

   private Context context;
   private IntentUtil util;

   private String name;
   private String subTitle;
   private String brief;
   private String appName;
   private String appTitle;

   private Bitmap photo;
   private Bitmap cover;
   private Bitmap appIcon;

   private int nameColor;
   private int subTitleColor;
   private int briefColor;
   private int iconColor;
   private int backgroundColor;

   private boolean showDivider = true;
   private int dividerColor = 0;
   private int dividerHeight = 4;
   private int dividerDashWidth = 15;
   private int dividerDashGap = 15;

   private boolean linksAnimated = true;
   private int linksColumnsCount = 5;
   private int actionsColumnsCount = 2;

   private boolean wrapScrollView = false;
   private boolean showAsCard = true;

   private LinkedList<Item> links = new LinkedList();
   private LinkedList<Item> actions = new LinkedList();

   /**
    * @deprecated Used {@link #with(Context)} instead.
    */
   @Deprecated
   AboutBuilder(Context context) {
       this.context = context;
       this.util = new IntentUtil(context);
   }

   public static AboutBuilder with(Context context) {
       //noinspection deprecation
       return new AboutBuilder(context);
   }

   private String getApplicationID() {
       return context.getPackageName();
   }

   private PackageInfo getPackageInfo() throws PackageManager.NameNotFoundException {
       return context.getPackageManager().getPackageInfo(getApplicationID(), 0);
   }

   /**
    * Gets the id of the last action added
    */
   public int getLastActionId() {
       return getLastAction().getId();
   }





   /**
    * Gets the last action item added
    */
   public Item getLastAction() {
       return actions.getLast();
   }

   /**
    * Gets the id of the last link added
    */
   public int getLastLinkId() {
       return getLastLink().getId();
   }

   /**
    * Gets the last link item added
    */
   public Item getLastLink() {
       return links.getLast();
   }




//
//    public AboutBuilder salesLink(String destination) {
//        return addLink(R.drawable.sales, R.string.sales, new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				//Toast.makeText(context, "Sales icon Pressed", Toast.LENGTH_LONG, Toast.INFO, true).show();
//			Intent intnt = new Intent(context, SalesFragmentsActivity.class);
//			context.startActivity(intnt);
//			}
//		});
//   }





   @NonNull
   public AboutBuilder setName(String text) {
       this.name = text;
       return this;
   }

   /**
    * Sets the developer name
    *
    * @param text the name
    * @return the same {@link AboutBuilder} instance
    */
   @NonNull
   public AboutBuilder setName(int text) {
       return setName(context.getString(text));
   }

   /**
    * Sets the sub title. It will be place below the developer name
    *
    * @param text the sub title
    * @return the same {@link AboutBuilder} instance
    */
   @NonNull
   public AboutBuilder setSubTitle(String text) {
       this.subTitle = text;
       return this;
   }

   /**
    * Sets the sub title. It will be place below the developer name
    *
    * @param text the sub title
    * @return the same {@link AboutBuilder} instance
    */
   @NonNull
   public AboutBuilder setSubTitle(int text) {
       return setSubTitle(context.getString(text));
   }

   /**
    * Sets a personal brief
    *
    * @param text the brief
    * @return the same {@link AboutBuilder} instance
    */
   @NonNull
   public AboutBuilder setBrief(String text) {
       this.brief = text;
       return this;
   }

   /**
    * Sets a personal brief
    *
    * @param text the brief
    * @return the same {@link AboutBuilder} instance
    */
   @NonNull
   public AboutBuilder setBrief(int text) {
       return setBrief(context.getString(text));
   }

   /**
    * Sets the app name
    *
    * @param text the app name
    * @return the same {@link AboutBuilder} instance
    */
   @NonNull
   public AboutBuilder setAppName(String text) {
       this.appName = text;
       return this;
   }

   /**
    * Sets the app name
    *
    * @param text the app name
    * @return the same {@link AboutBuilder} instance
    */
   @NonNull
   public AboutBuilder setAppName(int text) {
       return setAppName(context.getString(text));
   }

   /**
    * Sets the app title
    *
    * @param text the title
    * @return the same {@link AboutBuilder} instance
    */
   @NonNull
   public AboutBuilder setAppTitle(String text) {
       this.appTitle = text;
       return this;
   }

   /**
    * Sets the app title
    *
    * @param text the title
    * @return the same {@link AboutBuilder} instance
    */
   @NonNull
   public AboutBuilder setAppTitle(int text) {
       return setAppTitle(context.getString(text));
   }

   /**
    * Displays the app version below the app name
    *
    * @return the same {@link AboutBuilder} instance
    */
   @NonNull
   public AboutBuilder setVersionNameAsAppSubTitle() {
       try {
           return setAppTitle(context.getString(R.string.version, getPackageInfo().versionName));
       } catch (PackageManager.NameNotFoundException e) {
           return setAppTitle(R.string.error);
       }
   }

   /**
    * Sets the developer photo
    *
    * @param photo the image
    * @return the same {@link AboutBuilder} instance
    */
   @NonNull
   public AboutBuilder setPhoto(Bitmap photo) {
       this.photo = photo;
       return this;
   }

   /**
    * Sets the developer photo
    *
    * @param photo the image
    * @return the same {@link AboutBuilder} instance
    */
   @NonNull
   public AboutBuilder setPhoto(int photo) {
       return setPhoto(IconUtil.getBitmap(context, photo));
   }

   /**
    * Sets the developer photo
    *
    * @param photo the image
    * @return the same {@link AboutBuilder} instance
    */
   @NonNull
   public AboutBuilder setPhoto(@NonNull BitmapDrawable photo) {
       return setPhoto(IconUtil.getBitmap(photo));
   }

   /**
    * Sets a about cover
    *
    * @param cover the image
    * @return the same {@link AboutBuilder} instance
    */
   @NonNull
   public AboutBuilder setCover(Bitmap cover) {
       this.cover = cover;
       return this;
   }

   /**
    * Sets a about cover
    *
    * @param cover the image
    * @return the same {@link AboutBuilder} instance
    */
   @NonNull
   public AboutBuilder setCover(int cover) {
       return setCover(IconUtil.getBitmap(context, cover));
   }

   /**
    * Sets a about cover
    *
    * @param cover the image
    * @return the same {@link AboutBuilder} instance
    */
   @NonNull
   public AboutBuilder setCover(@NonNull BitmapDrawable cover) {
       return setCover(IconUtil.getBitmap(cover));
   }

   /**
    * Sets an icon to display as app icon
    *
    * @param icon the app icon
    * @return the same {@link AboutBuilder} instance
    */
   @NonNull
   public AboutBuilder setAppIcon(Bitmap icon) {
       this.appIcon = icon;
       return this;
   }

   /**
    * Sets an icon to display as app icon
    *
    * @param icon the app icon
    * @return the same {@link AboutBuilder} instance
    */
   @NonNull
   public AboutBuilder setAppIcon(int icon) {
       return setAppIcon(IconUtil.getBitmap(context, icon));
   }

   /**
    * Sets an icon to display as app icon
    *
    * @param icon the app icon
    * @return the same {@link AboutBuilder} instance
    */
   @NonNull
   public AboutBuilder setAppIcon(@NonNull BitmapDrawable icon) {
       return setAppIcon(IconUtil.getBitmap(icon));
   }

   /**
    * Sets the name text color
    *
    * @param color the color resource or the real color.
    * @return the same {@link AboutBuilder} instance
    */
   @NonNull
   public AboutBuilder setNameColor(int color) {
       this.nameColor = ColorUtil.get(context, color);
       return this;
   }

   /**
    * Sets the sub title text color
    *
    * @param color the color resource or the real color.
    * @return the same {@link AboutBuilder} instance
    */
   @NonNull
   public AboutBuilder setSubTitleColor(int color) {
       this.subTitleColor = ColorUtil.get(context, color);
       return this;
   }

   /**
    * Sets the brief text color
    *
    * @param color the color resource or the real color.
    * @return the same {@link AboutBuilder} instance
    */
   @NonNull
   public AboutBuilder setBriefColor(int color) {
       this.briefColor = ColorUtil.get(context, color);
       return this;
   }

   /**
    * Sets the divider color
    *
    * @param color the color resource or the real color.
    * @return the same {@link AboutBuilder} instance
    */
   @NonNull
   public AboutBuilder setDividerColor(int color) {
       this.dividerColor = ColorUtil.get(context, color);
       return this;
   }

   /**
    * Sets the icons color
    *
    * @param color the color resource or the real color.
    * @return the same {@link AboutBuilder} instance
    */
   @NonNull
   public AboutBuilder setIconColor(int color) {
       this.iconColor = ColorUtil.get(context, color);
       return this;
   }

   /**
    * Sets the about view background color
    *
    * @param color the color resource or the real color.
    * @return the same {@link AboutBuilder} instance
    */
   @NonNull
   public AboutBuilder setBackgroundColor(int color) {
       this.backgroundColor = ColorUtil.get(context, color);
       return this;
   }

   /**
    * Sets the maximum number of columns for the actions section
    *
    * @param count number of columns
    * @return the same {@link AboutBuilder} instance
    */
   @NonNull
   public AboutBuilder setActionsColumnsCount(int count) {
       this.actionsColumnsCount = count;
       return this;
   }


   /**
    * Sets the maximum number of columns for the links section
    *
    * @param count number of columns
    * @return the same {@link AboutBuilder} instance
    */
   @NonNull
   public AboutBuilder setLinksColumnsCount(int count) {
       this.linksColumnsCount = count;
       return this;
   }

   /**
    * Sets an animation when displaying the actions
    *
    * @param animate true if you want it
    * @return the same {@link AboutBuilder} instance
    */
   @NonNull
   public AboutBuilder setLinksAnimated(boolean animate) {
       this.linksAnimated = animate;
       return this;
   }

   /**
    * Sets the divider height
    *
    * @param dividerHeight size of the height
    * @return the same {@link AboutBuilder} instance
    */
   @NonNull
   public AboutBuilder setDividerHeight(int dividerHeight) {
       this.dividerHeight = dividerHeight;
       return this;
   }

   /**
    * Sets the divider dash width
    *
    * @param dividerDashWidth size of the width
    * @return the same {@link AboutBuilder} instance
    */
   @NonNull
   public AboutBuilder setDividerDashWidth(int dividerDashWidth) {
       this.dividerDashWidth = dividerDashWidth;
       return this;
   }

   /**
    * Sets the divider dash gap
    *
    * @param dividerDashGap size of the gap
    * @return the same {@link AboutBuilder} instance
    */
   @NonNull
   public AboutBuilder setDividerDashGap(int dividerDashGap) {
       this.dividerDashGap = dividerDashGap;
       return this;
   }

   /**
    * Displays a divider line between the about sections
    *
    * @param showDivider true if you want it
    * @return the same {@link AboutBuilder} instance
    */
   @NonNull
   public AboutBuilder setShowDivider(boolean showDivider) {
       this.showDivider = showDivider;
       return this;
   }

   /**
    * Places the about view inside a {@link android.widget.ScrollView}
    *
    * @param wrapScrollView true if you want to wrap
    * @return the same {@link AboutBuilder} instance
    */
   @NonNull
   public AboutBuilder setWrapScrollView(boolean wrapScrollView) {
       this.wrapScrollView = wrapScrollView;
       return this;
   }

   /**
    * Adds an link on the links section.
    *
    * @param icon            the action icon
    * @param label           the action title
    * @param onClickListener the click callback
    * @return the same {@link AboutBuilder} instance
    */
   @NonNull
   public AboutBuilder addLink(Bitmap icon, String label, OnClickListener onClickListener) {
       links.add(new Item(icon, label, onClickListener));
       return this;
   }

   /**
    * Adds an link on the links section.
    *
    * @param icon   the action icon
    * @param label  the action title
    * @param intent the action intent
    * @return the same {@link AboutBuilder} instance
    */
   @NonNull
   public AboutBuilder addLink(Bitmap icon, String label, Intent intent) {
       return addLink(icon, label, util.clickIntent(intent));
   }

   /**
    * Adds an link on the links section.
    *
    * @param icon  the action icon
    * @param label the action title
    * @param uri   the action uri
    * @return the same {@link AboutBuilder} instance
    */
   @NonNull
   public AboutBuilder addLink(Bitmap icon, String label, Uri uri) {
       return addLink(icon, label, util.clickUri(uri));
   }

   /**
    * Adds an link on the links section.
    *
    * @param icon  the action icon
    * @param label the action title
    * @param url   any url
    * @return the same {@link AboutBuilder} instance
    */
   @NonNull
   public AboutBuilder addLink(Bitmap icon, String label, String url) {
       return addLink(icon, label, Uri.parse(url));
   }

   /**
    * Adds an link on the links section.
    *
    * @param icon            the action icon
    * @param label           the action title
    * @param onClickListener the click callback
    * @return the same {@link AboutBuilder} instance
    */
   @NonNull
   public AboutBuilder addLink(Bitmap icon, int label, OnClickListener onClickListener) {
       return addLink(icon, context.getString(label), onClickListener);
   }

   /**
    * Adds an link on the links section.
    *
    * @param icon   the action icon
    * @param label  the action title
    * @param intent the action intent
    * @return the same {@link AboutBuilder} instance
    */
   @NonNull
   public AboutBuilder addLink(Bitmap icon, int label, Intent intent) {
       return addLink(icon, label, util.clickIntent(intent));
   }

   /**
    * Adds an link on the links section.
    *
    * @param icon  the action icon
    * @param label the action title
    * @param uri   the action uri
    * @return the same {@link AboutBuilder} instance
    */
   @NonNull
   public AboutBuilder addLink(Bitmap icon, int label, Uri uri) {
       return addLink(icon, label, util.clickUri(uri));
   }

   /**
    * Adds an link on the links section.
    *
    * @param icon  the action icon
    * @param label the action title
    * @param url   any url
    * @return the same {@link AboutBuilder} instance
    */
   @NonNull
   public AboutBuilder addLink(Bitmap icon, int label, String url) {
       return addLink(icon, label, Uri.parse(url));
   }

   /**
    * Adds an link on the links section.
    *
    * @param icon            the action icon
    * @param label           the action title
    * @param onClickListener the click callback
    * @return the same {@link AboutBuilder} instance
    */
   @NonNull
   public AboutBuilder addLink(int icon, int label, OnClickListener onClickListener) {
       return addLink(IconUtil.getBitmap(context, icon), context.getString(label), onClickListener);
   }

   /**
    * Adds an link on the links section.
    *
    * @param icon   the action icon
    * @param label  the action title
    * @param intent the action intent
    * @return the same {@link AboutBuilder} instance
    */
   @NonNull
   public AboutBuilder addLink(int icon, int label, Intent intent) {
       return addLink(icon, label, util.clickIntent(intent));
   }

   /**
    * Adds an link on the links section.
    *
    * @param icon  the action icon
    * @param label the action title
    * @param uri   the action uri
    * @return the same {@link AboutBuilder} instance
    */
   @NonNull
   public AboutBuilder addLink(int icon, int label, Uri uri) {
       return addLink(icon, label, util.clickUri(uri));
   }

   /**
    * Adds an link on the links section.
    *
    * @param icon  the action icon
    * @param label the action title
    * @param url   any url
    * @return the same {@link AboutBuilder} instance
    */
   @NonNull
   public AboutBuilder addLink(int icon, int label, String url) {
       return addLink(icon, label, Uri.parse(url));
   }

   /**
    * Adds an link on the links section.
    *
    * @param icon            the action icon
    * @param label           the action title
    * @param onClickListener the click callback
    * @return the same {@link AboutBuilder} instance
    */
   @NonNull
   public AboutBuilder addLink(int icon, String label, OnClickListener onClickListener) {
       return addLink(IconUtil.getBitmap(context, icon), label, onClickListener);
   }

   /**
    * Adds an link on the links section.
    *
    * @param icon   the action icon
    * @param label  the action title
    * @param intent the action intent
    * @return the same {@link AboutBuilder} instance
    */
   @NonNull
   public AboutBuilder addLink(int icon, String label, Intent intent) {
       return addLink(icon, label, util.clickIntent(intent));
   }

   /**
    * Adds an link on the links section.
    *
    * @param icon  the action icon
    * @param label the action title
    * @param uri   the action uri
    * @return the same {@link AboutBuilder} instance
    */
   @NonNull
   public AboutBuilder addLink(int icon, String label, Uri uri) {
       return addLink(icon, label, util.clickUri(uri));
   }

   /**
    * Adds an link on the links section.
    *
    * @param icon  the action icon
    * @param label the action title
    * @param url   any url
    * @return the same {@link AboutBuilder} instance
    */
   @NonNull
   public AboutBuilder addLink(int icon, String label, String url) {
       return addLink(icon, label, Uri.parse(url));
   }

   /**
    * Adds an link on the links section.
    *
    * @param icon            the action icon
    * @param label           the action title
    * @param onClickListener the click callback
    * @return the same {@link AboutBuilder} instance
    */
   @NonNull
   public AboutBuilder addLink(@NonNull BitmapDrawable icon, int label, OnClickListener onClickListener) {
       return addLink(IconUtil.getBitmap(icon), context.getString(label), onClickListener);
   }

   /**
    * Adds an link on the links section.
    *
    * @param icon   the action icon
    * @param label  the action title
    * @param intent the action intent
    * @return the same {@link AboutBuilder} instance
    */
   @NonNull
   public AboutBuilder addLink(@NonNull BitmapDrawable icon, int label, Intent intent) {
       return addLink(icon, label, util.clickIntent(intent));
   }

   /**
    * Adds an link on the links section.
    *
    * @param icon  the action icon
    * @param label the action title
    * @param uri   the action uri
    * @return the same {@link AboutBuilder} instance
    */
   @NonNull
   public AboutBuilder addLink(@NonNull BitmapDrawable icon, int label, Uri uri) {
       return addLink(icon, label, util.clickUri(uri));
   }

   /**
    * Adds an link on the links section.
    *
    * @param icon  the action icon
    * @param label the action title
    * @param url   any url
    * @return the same {@link AboutBuilder} instance
    */
   @NonNull
   public AboutBuilder addLink(@NonNull BitmapDrawable icon, int label, String url) {
       return addLink(icon, label, Uri.parse(url));
   }

   /**
    * Adds an link on the links section.
    *
    * @param icon            the action icon
    * @param label           the action title
    * @param onClickListener the click callback
    * @return the same {@link AboutBuilder} instance
    */
   @NonNull
   public AboutBuilder addLink(@NonNull BitmapDrawable icon, String label, OnClickListener onClickListener) {
       return addLink(IconUtil.getBitmap(icon), label, onClickListener);
   }

   /**
    * Adds an link on the links section.
    *
    * @param icon   the action icon
    * @param label  the action title
    * @param intent the action intent
    * @return the same {@link AboutBuilder} instance
    */
   @NonNull
   public AboutBuilder addLink(@NonNull BitmapDrawable icon, String label, Intent intent) {
       return addLink(icon, label, util.clickIntent(intent));
   }

   /**
    * Adds an link on the links section.
    *
    * @param icon  the action icon
    * @param label the action title
    * @param uri   the action uri
    * @return the same {@link AboutBuilder} instance
    */
   @NonNull
   public AboutBuilder addLink(@NonNull BitmapDrawable icon, String label, Uri uri) {
       return addLink(icon, label, util.clickUri(uri));
   }

   /**
    * Adds an link on the links section.
    *
    * @param icon  the action icon
    * @param label the action title
    * @param url   any url
    * @return the same {@link AboutBuilder} instance
    */
   @NonNull
   public AboutBuilder addLink(@NonNull BitmapDrawable icon, String label, String url) {
       return addLink(icon, label, Uri.parse(url));
   }


   /**
    * Adds a GitHub profile link on the links section
    *
    * @param user a GitHub user name
    * @return the same {@link AboutBuilder} instance
    */

   /**
    * Adds a Bitbucket profile link on the links section
    *
    * @param user a Bitbucket user name
    * @return the same {@link AboutBuilder} instance
    */
   @NonNull
   public AboutBuilder addBitbucketLink(int user) {
       return addBitbucketLink(context.getString(user));
   }

   /**
    * Adds a Bitbucket profile link on the links section
    *
    * @param user a Bitbucket user name
    * @return the same {@link AboutBuilder} instance
    */
   @NonNull
   public AboutBuilder addBitbucketLink(String user) {
       return addLink(R.drawable.about_profile_picture, "Bit Bucket", util.uri(R.string.url_bitbucket, user));
   }

   /**
    * Adds a Facebook profile link on the links section
    *
    * @param user a Facebook user name
    * @return the same {@link AboutBuilder} instance
    */


   /**
    * Adds a Instagram profile link on the links section
    *
    * @param user a Instagram user name
    * @return the same {@link AboutBuilder} instance
    */


   /**
    * Adds a Twitter profile link on the links section
    *
    * @param user a Twitter user name
    * @return the same {@link AboutBuilder} instance
    */


   /**
    * Adds a Google link on the links section
    *
    * @param url any url
    * @return the same {@link AboutBuilder} instance
    */
   @NonNull
   public AboutBuilder addGoogleLink(int url) {
       return addGoogleLink(context.getString(url));
   }

   /**
    * Adds a Google link on the links section
    *
    * @param url any url
    * @return the same {@link AboutBuilder} instance
    */
   @NonNull
   public AboutBuilder addGoogleLink(String url) {
       return addLink(R.drawable.about_google, "Google", url);
   }

   /**
    * Adds a Google Plus profile link on the links section
    *
    * @param user a Google Plus user name
    * @return the same {@link AboutBuilder} instance
    */
   @NonNull
   public AboutBuilder addGooglePlusLink(int user) {
       return addGooglePlusLink(context.getString(user));
   }

   /**
    * Adds a Google Plus profile link on the links section
    *
    * @param user a Google Plus user name or id
    * @return the same {@link AboutBuilder} instance
    */
   @NonNull
   public AboutBuilder addGooglePlusLink(String user) {
       return addLink(R.drawable.about_google, "Google Plus", util.openGooglePlus(user));
   }




   /**
    * Adds a Youtube user link on the links section
    *
    * @param user a Youtube user name
    * @return the same {@link AboutBuilder} instance
    */




   /**
    * Adds a e-mail link on the links section
    *
    * @param email   addressee e-mail
    * @param subject the subject of the e-mail
    * @param message the contect of the e-mail
    * @return the same {@link AboutBuilder} instance
    */

   /**
    * Adds an action button on the actions section.
    *
    * @param icon            the action icon
    * @param label           the action title
    * @param onClickListener the click callback
    * @return the same {@link AboutBuilder} instance
    */
   @NonNull
   public AboutBuilder addAction(Bitmap icon, String label, OnClickListener onClickListener) {
       actions.add(new Item(icon, label, onClickListener));
       return this;
   }

   /**
    * Adds an action button on the actions section.
    *
    * @param icon   the action icon
    * @param label  the action title
    * @param intent the action intent
    * @return the same {@link AboutBuilder} instance
    */
   @NonNull
   public AboutBuilder addAction(Bitmap icon, String label, Intent intent) {
       return addAction(icon, label, util.clickIntent(intent));
   }

   /**
    * Adds an action button on the actions section.
    *
    * @param icon  the action icon
    * @param label the action title
    * @param uri   the action uri
    * @return the same {@link AboutBuilder} instance
    */
   @NonNull
   public AboutBuilder addAction(Bitmap icon, String label, Uri uri) {
       return addAction(icon, label, util.clickUri(uri));
   }

   /**
    * Adds an action button on the actions section.
    *
    * @param icon  the action icon
    * @param label the action title
    * @param url   any url to browse
    * @return the same {@link AboutBuilder} instance
    */
   @NonNull
   public AboutBuilder addAction(Bitmap icon, String label, String url) {
       return addAction(icon, label, Uri.parse(url));
   }

   /**
    * Adds an action button on the actions section.
    *
    * @param icon            the action icon
    * @param label           the action title
    * @param onClickListener the click callback
    * @return the same {@link AboutBuilder} instance
    */
   @NonNull
   public AboutBuilder addAction(Bitmap icon, int label, OnClickListener onClickListener) {
       return addAction(icon, context.getString(label), onClickListener);
   }

   /**
    * Adds an action button on the actions section.
    *
    * @param icon   the action icon
    * @param label  the action title
    * @param intent the action intent
    * @return the same {@link AboutBuilder} instance
    */
   @NonNull
   public AboutBuilder addAction(Bitmap icon, int label, Intent intent) {
       return addAction(icon, label, util.clickIntent(intent));
   }

   /**
    * Adds an action button on the actions section.
    *
    * @param icon  the action icon
    * @param label the action title
    * @param uri   the action uri
    * @return the same {@link AboutBuilder} instance
    */
   @NonNull
   public AboutBuilder addAction(Bitmap icon, int label, Uri uri) {
       return addAction(icon, label, util.clickUri(uri));
   }

   /**
    * Adds an action button on the actions section.
    *
    * @param icon  the action icon
    * @param label the action title
    * @param url   any url to browse
    * @return the same {@link AboutBuilder} instance
    */
   @NonNull
   public AboutBuilder addAction(Bitmap icon, int label, String url) {
       return addAction(icon, label, Uri.parse(url));
   }

   /**
    * Adds an action button on the actions section.
    *
    * @param icon            the action icon
    * @param label           the action title
    * @param onClickListener the click callback
    * @return the same {@link AboutBuilder} instance
    */
   @NonNull
   public AboutBuilder addAction(int icon, int label, OnClickListener onClickListener) {
       return addAction(IconUtil.getBitmap(context, icon), context.getString(label), onClickListener);
   }

   /**
    * Adds an action button on the actions section.
    *
    * @param icon   the action icon
    * @param label  the action title
    * @param intent the action intent
    * @return the same {@link AboutBuilder} instance
    */
   @NonNull
   public AboutBuilder addAction(int icon, int label, Intent intent) {
       return addAction(icon, label, util.clickIntent(intent));
   }

   /**
    * Adds an action button on the actions section.
    *
    * @param icon  the action icon
    * @param label the action title
    * @param uri   the action uri
    * @return the same {@link AboutBuilder} instance
    */
   @NonNull
   public AboutBuilder addAction(int icon, int label, Uri uri) {
       return addAction(icon, label, util.clickUri(uri));
   }

   /**
    * Adds an action button on the actions section.
    *
    * @param icon  the action icon
    * @param label the action title
    * @param url   any url to browse
    * @return the same {@link AboutBuilder} instance
    */
   @NonNull
   public AboutBuilder addAction(int icon, int label, String url) {
       return addAction(icon, label, Uri.parse(url));
   }

   /**
    * Adds an action button on the actions section.
    *
    * @param icon            the action icon
    * @param label           the action title
    * @param onClickListener the click callback
    * @return the same {@link AboutBuilder} instance
    */
   @NonNull
   public AboutBuilder addAction(int icon, String label, OnClickListener onClickListener) {
       return addAction(IconUtil.getBitmap(context, icon), label, onClickListener);
   }

   /**
    * Adds an action button on the actions section.
    *
    * @param icon   the action icon
    * @param label  the action title
    * @param intent the action intent
    * @return the same {@link AboutBuilder} instance
    */
   @NonNull
   public AboutBuilder addAction(int icon, String label, Intent intent) {
       return addAction(icon, label, util.clickIntent(intent));
   }

   /**
    * Adds an action button on the actions section.
    *
    * @param icon  the action icon
    * @param label the action title
    * @param uri   the action uri
    * @return the same {@link AboutBuilder} instance
    */
   @NonNull
   public AboutBuilder addAction(int icon, String label, Uri uri) {
       return addAction(icon, label, util.clickUri(uri));
   }

   /**
    * Adds an action button on the actions section.
    *
    * @param icon  the action icon
    * @param label the action title
    * @param url   any url to browse
    * @return the same {@link AboutBuilder} instance
    */
   @NonNull
   public AboutBuilder addAction(int icon, String label, String url) {
       return addAction(icon, label, Uri.parse(url));
   }

   /**
    * Adds an action button on the actions section.
    *
    * @param icon            the action icon
    * @param label           the action title
    * @param onClickListener the click callback
    * @return the same {@link AboutBuilder} instance
    */
   @NonNull
   public AboutBuilder addAction(@NonNull BitmapDrawable icon, int label, OnClickListener onClickListener) {
       return addAction(IconUtil.getBitmap(icon), context.getString(label), onClickListener);
   }

   /**
    * Adds an action button on the actions section.
    *
    * @param icon   the action icon
    * @param label  the action title
    * @param intent the action intent
    * @return the same {@link AboutBuilder} instance
    */
   @NonNull
   public AboutBuilder addAction(@NonNull BitmapDrawable icon, int label, Intent intent) {
       return addAction(icon, label, util.clickIntent(intent));
   }

   /**
    * Adds an action button on the actions section.
    *
    * @param icon  the action icon
    * @param label the action title
    * @param uri   the action uri
    * @return the same {@link AboutBuilder} instance
    */
   @NonNull
   public AboutBuilder addAction(@NonNull BitmapDrawable icon, int label, Uri uri) {
       return addAction(icon, label, util.clickUri(uri));
   }

   /**
    * Adds an action button on the actions section.
    *
    * @param icon  the action icon
    * @param label the action title
    * @param url   any url to browse
    * @return the same {@link AboutBuilder} instance
    */
   @NonNull
   public AboutBuilder addAction(@NonNull BitmapDrawable icon, int label, String url) {
       return addAction(icon, label, Uri.parse(url));
   }

   /**
    * Adds an action button on the actions section.
    *
    * @param icon            the action icon
    * @param label           the action title
    * @param onClickListener the click callback
    * @return the same {@link AboutBuilder} instance
    */
   @NonNull
   public AboutBuilder addAction(@NonNull BitmapDrawable icon, String label, OnClickListener onClickListener) {
       return addAction(IconUtil.getBitmap(icon), label, onClickListener);
   }

   /**
    * Adds an action button on the actions section.
    *
    * @param icon   the action icon
    * @param label  the action title
    * @param intent the action intent
    * @return the same {@link AboutBuilder} instance
    */
   @NonNull
   public AboutBuilder addAction(@NonNull BitmapDrawable icon, String label, Intent intent) {
       return addAction(icon, label, util.clickIntent(intent));
   }

   /**
    * Adds an action button on the actions section.
    *
    * @param icon  the action icon
    * @param label the action title
    * @param uri   the action uri
    * @return the same {@link AboutBuilder} instance
    */
   @NonNull
   public AboutBuilder addAction(@NonNull BitmapDrawable icon, String label, Uri uri) {
       return addAction(icon, label, util.clickUri(uri));
   }

   /**
    * Adds an action button on the actions section.
    *
    * @param icon  the action icon
    * @param label the action title
    * @param url   any url to browse
    * @return the same {@link AboutBuilder} instance
    */
   @NonNull
   public AboutBuilder addAction(@NonNull BitmapDrawable icon, String label, String url) {
       return addAction(icon, label, Uri.parse(url));
   }

   /**
    * Adds an action button to rate the app on Google Play Store
    *
    * @param appId the application id
    * @return the same {@link AboutBuilder} instance
    */
   @NonNull
   public AboutBuilder addFiveStarsAction(int appId) {
       return addFiveStarsAction(context.getString(appId));
   }

   /**
    * Adds an action button to rate the app on Google Play Store
    *
    * @param appId the application id
    * @return the same {@link AboutBuilder} instance
    */
   @NonNull
   public AboutBuilder addFiveStarsAction(String appId) {
       return addAction(R.drawable.about_star, R.string.rate_five_stars, util.openPlayStoreAppPage(appId));
   }

   /**
    * Adds an action button to rate the app on Google Play Store.
    * This method use the default application id from the context
    *
    * @return the same {@link AboutBuilder} instance
    */
   @NonNull
   public AboutBuilder addFiveStarsAction() {
       return addFiveStarsAction(getApplicationID());
   }

   /**
    * Adds an action button to update the app using Google Play Store
    *
    * @param appId the application id
    * @return the same {@link AboutBuilder} instance
    */
   @NonNull
   public AboutBuilder addUpdateAction(int appId) {
       return addUpdateAction(context.getString(appId));
   }


   /**
    * Adds an action button to update the app using Google Play Store
    *
    * @param appId the application id
    * @return the same {@link AboutBuilder} instance
    */
   @NonNull
   public AboutBuilder addUpdateAction(String appId) {
       return addAction(R.drawable.about_update, "Update Application", util.openPlayStoreAppPage(appId));
   }

   /**
    * Adds an action button to update the app using Google Play Store
    * This method use the default application id from the context
    *
    * @return the same {@link AboutBuilder} instance
    */
   @NonNull
   public AboutBuilder addUpdateAction() {
       return addUpdateAction(getApplicationID());
   }

   /**
    * Adds an action button to browse more apps from the developer
    *
    * @param userName the developer user name
    * @return the same {@link AboutBuilder} instance
    */


   /**
    * Adds a share action button
    *
    * @param subject the subject
    * @param message the content message
    * @return the same {@link AboutBuilder} instance
    */
   @NonNull
   public AboutBuilder addShareAction(int subject, int message) {
       return addShareAction(context.getString(subject), context.getString(message));
   }

   /**
    * Adds a share action button
    *
    * @param subject the subject
    * @param message the content message
    * @return the same {@link AboutBuilder} instance
    */
   @NonNull
   public AboutBuilder addShareAction(String subject, String message) {
       return addAction(R.drawable.about_share, "Share", util.shareThisApp(subject, message));
   }

   /**
    * Adds a share action button. No content message will be placed
    *
    * @param subject the subject
    * @return the same {@link AboutBuilder} instance
    */
   @NonNull
   public AboutBuilder addShareAction(String subject) {
       return addShareAction(subject, context.getString(R.string.version, context.getPackageName()));
   }

   /**
    * Adds a share action button. No content message will be placed
    *
    * @param subject the subject
    * @return the same {@link AboutBuilder} instance
    */
   @NonNull
   public AboutBuilder addShareAction(int subject) {
       return addShareAction(context.getString(subject));
   }


   /**
    * Adds a feedback action button
    *
    * @param email   the developer e-mail
    * @param subject the subject
    * @param content the e-mail content
    * @return the same {@link AboutBuilder} instance
    */
   @NonNull
   public AboutBuilder addFeedbackAction(int email, int subject, int content) {
       return addFeedbackAction(context.getString(email), context.getString(subject), context.getString(content));
   }

   /**
    * Adds a feedback action button
    *
    * @param email   the developer e-mail
    * @param subject the subject
    * @param content the e-mail content
    * @return the same {@link AboutBuilder} instance
    */
   @NonNull
   public AboutBuilder addFeedbackAction(int email, String subject, String content) {
       return addAction(R.drawable.about_feedback, "Facebook", util.sendEmail(context.getString(email), subject, content));
   }

   /**
    * Adds a feedback action button
    *
    * @param email   the developer e-mail
    * @param subject the subject
    * @param content the e-mail content
    * @return the same {@link AboutBuilder} instance
    */
   @NonNull
   public AboutBuilder addFeedbackAction(String email, String subject, String content) {
       return addAction(R.drawable.about_feedback, "Facebook", util.sendEmail(email, subject, content));
   }

   /**
    * Adds a feedback action button. No content message will be placed
    *
    * @param email   the developer e-mail
    * @param subject the subject
    * @return the same {@link AboutBuilder} instance
    */
   @NonNull
   public AboutBuilder addFeedbackAction(int email, int subject) {
       return addFeedbackAction(context.getString(email), context.getString(subject));
   }

   /**
    * Adds a feedback action button. No content message will be placed
    *
    * @param email   the developer e-mail
    * @param subject the subject
    * @return the same {@link AboutBuilder} instance
    */
   @NonNull
   public AboutBuilder addFeedbackAction(String email, String subject) {
       return addFeedbackAction(email, subject, null);
   }

   /**
    * Adds a feedback action button. No content message will be placed
    *
    * @param email   the developer e-mail
    * @param subject the subject
    * @return the same {@link AboutBuilder} instance
    */
   @NonNull
   public AboutBuilder addFeedbackAction(int email, String subject) {
       return addFeedbackAction(context.getString(email), subject, null);
   }

   /**
    * Adds a feedback action button. No subject or content message will be placed
    *
    * @param email the developer e-mail
    * @return the same {@link AboutBuilder} instance
    */
   @NonNull
   public AboutBuilder addFeedbackAction(int email) {
       return addFeedbackAction(context.getString(email));
   }

   /**
    * Adds a feedback action button. No subject or content message will be placed
    *
    * @param email the developer e-mail
    * @return the same {@link AboutBuilder} instance
    */
   @NonNull
   public AboutBuilder addFeedbackAction(String email) {
       return addFeedbackAction(email, null);
   }

   /**
    * Adds an introduce action button
    *
    * @param onClickListener the click callback
    * @return the same {@link AboutBuilder} instance
    */
   @NonNull
   public AboutBuilder addIntroduceAction(OnClickListener onClickListener) {
       return addAction(R.drawable.about_intrduce, "Introduction", onClickListener);
   }

   /**
    * Adds an introduce action button
    *
    * @param intent the action intent
    * @return the same {@link AboutBuilder} instance
    */
   @NonNull
   public AboutBuilder addIntroduceAction(Intent intent) {
       return addIntroduceAction(util.clickIntent(intent));
   }

   /**
    * Adds an help action button
    *
    * @param onClickListener the click callback
    * @return the same {@link AboutBuilder} instance
    */
   @NonNull
   public AboutBuilder addHelpAction(OnClickListener onClickListener) {
       return addAction(R.drawable.about_help, "Help", onClickListener);
   }

   /**
    * Adds an help action button
    *
    * @param intent the action intent
    * @return the same {@link AboutBuilder} instance
    */
   @NonNull
   public AboutBuilder addHelpAction(Intent intent) {
       return addHelpAction(util.clickIntent(intent));
   }

   /**
    * Adds an license action button
    *
    * @param onClickListener the click callback
    * @return the same {@link AboutBuilder} instance
    */
   @NonNull
   public AboutBuilder addLicenseAction(OnClickListener onClickListener) {
       return addAction(R.drawable.about_license, "License", onClickListener);
   }

   /**
    * Adds an license action button
    *
    * @param intent the action intent
    * @return the same {@link AboutBuilder} instance
    */
   @NonNull
   public AboutBuilder addLicenseAction(Intent intent) {
       return addLicenseAction(util.clickIntent(intent));
   }




   /**
    * Adds an remove ads action button
    *
    * @param onClickListener the click callback
    * @return the same {@link AboutBuilder} instance
    */
   @NonNull
   public AboutBuilder addRemoveAdsAction(OnClickListener onClickListener) {
       return addAction(R.drawable.about_ads, "Remove Ads", onClickListener);
   }


   /**
    * Adds an remove ads action button
    *
    * @param intent the action intent
    * @return the same {@link AboutBuilder} instance
    */
   @NonNull
   public AboutBuilder addRemoveAdsAction(Intent intent) {
       return addRemoveAdsAction(util.clickIntent(intent));
   }

   /**
    * Adds an donate action button
    *
    * @param onClickListener the click callback
    * @return the same {@link AboutBuilder} instance
    */
   @NonNull
   public AboutBuilder addDonateAction(OnClickListener onClickListener) {
       return addAction(R.drawable.about_donate, "Donate", onClickListener);
   }

   /**
    * Adds an donate action button
    *
    * @param intent the action intent
    * @return the same {@link AboutBuilder} instance
    */
   @NonNull
   public AboutBuilder addDonateAction(Intent intent) {
       return addDonateAction(util.clickIntent(intent));
   }



   @NonNull
   public AboutBuilder setShowAsCard(boolean showAsCard) {
       this.showAsCard = showAsCard;
       return this;
   }

   /**
    * Adds a privacy policy action button
    *
    * @param url the url to privacy policy web page
    * @return the same {@link AboutBuilder} instance
    */
   @NonNull
   public AboutBuilder addPrivacyPolicyAction(String url) {
       return addAction(R.drawable.about_privacy,  "Privacy", util.intent(url));
   }

   /**
    * Adds a privacy policy action button
    *
    * @param onClickListener the click callback
    * @return the same {@link AboutBuilder} instance
    */
   @NonNull
   public AboutBuilder addPrivacyPolicyAction(OnClickListener onClickListener) {
       return addAction(R.drawable.about_privacy, "Privacy", onClickListener);
   }

   /**
    * Adds a privacy policy action button
    *
    * @param intent the action intent
    * @return the same {@link AboutBuilder} instance
    */
   @NonNull
   public AboutBuilder addPrivacyPolicyAction(Intent intent) {
       return addAction(R.drawable.about_privacy, "Privacy", util.clickIntent(intent));
   }

   public boolean isShowAsCard() {
       return showAsCard;
   }

   public String getName() {
       return name;
   }

   public String getSubTitle() {
       return subTitle;
   }

   public String getBrief() {
       return brief;
   }

   public String getAppName() {
       return appName;
   }

   public String getAppTitle() {
       return appTitle;
   }

   public Bitmap getPhoto() {
       return photo;
   }

   public Bitmap getCover() {
       return cover;
   }

   public Bitmap getAppIcon() {
       return appIcon;
   }

   public int getNameColor() {
       return nameColor;
   }

   public int getSubTitleColor() {
       return subTitleColor;
   }

   public int getBriefColor() {
       return briefColor;
   }

   public int getDividerColor() {
       return dividerColor;
   }

   public int getIconColor() {
       return iconColor;
   }

   public int getBackgroundColor() {
       return backgroundColor;
   }

   public int getLinksColumnsCount() {
       return linksColumnsCount;
   }

   public int getActionsColumnsCount() {
       return actionsColumnsCount;
   }

   public boolean isShowDivider() {
       return showDivider;
   }

   public int getDividerHeight() {
       return dividerHeight;
   }

   public int getDividerDashWidth() {
       return dividerDashWidth;
   }

   public int getDividerDashGap() {
       return dividerDashGap;
   }

   public boolean isLinksAnimated() {
       return linksAnimated;
   }

   public boolean isWrapScrollView() {
       return wrapScrollView;
   }

   @NonNull
   public LinkedList<Item> getLinks() {
       return links;
   }

   @NonNull
   public LinkedList<Item> getActions() {
       return actions;
   }

   @NonNull
   public AboutView build(String strscreen) {
       AboutView aboutView = new AboutView(context);
       if(strscreen.equalsIgnoreCase("Home")) {
       aboutView.buildHome(this);
       }else {
           aboutView.buildAdmin(this);

       }
       return aboutView;
   }

}
