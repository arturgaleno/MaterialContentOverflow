# MaterialContentOverflow

<img src="./contentoverflow.gif" width="480" height="360"></img>

## Usage

MaterialContentOverflow is a ViewGroup that implement the Persistent Bottom Sheets pattern of Google Material Design.
So you can put views inside it, and the MaterialContentOverflow will be displayed at bottom of screen.
With a sliding the view to up will show the content, and sliding to to bottom will hide.
The Google Material Design related to this view can be see [there](http://www.google.com/design/spec/components/bottom-sheets.html#bottom-sheets-persistent-bottom-sheets).
See the example app for more details.

```xml
<com.materialoverflowcontent.MaterialContentOverflow xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    app:fabButtonColor="@color/fab"
    app:buttonDrawable="@drawable/abc_btn_rating_star_off_mtrl_alpha"
    app:contentColor="@color/primary"
    app:buttonPosition="right">

    ...

<\com.materialoverflowcontent.MaterialContentOverflow>
```

## Attributes

fabButtonColor - Determines the color of the FloatingActionButton.
buttonDrawable - The icon that will show inside FloatingActionButton.
contentColor - The background color of overflow content.
buttonPosition - The position can be, "center", right" or "left".

## Gradle

```
compile 'com.materialcontentoverflow:materialcontentoverflow:1.0.0'
```