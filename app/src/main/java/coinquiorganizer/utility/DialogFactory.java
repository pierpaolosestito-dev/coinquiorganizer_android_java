package coinquiorganizer.utility;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Html;
import android.widget.ImageView;

import firstexample.helloworld.R;

public abstract class DialogFactory {

    public static int WARNING = 0;
    public static int SUCCESS = 1;
    public static int INFO = 2;

    private static AlertDialog createAlert(Context context,String message){
        AlertDialog alertDialog = new AlertDialog.Builder(context,AlertDialog.THEME_DEVICE_DEFAULT_LIGHT).create();
        alertDialog.setMessage(Html.fromHtml("<h3>"+message+"</h3>"));
        alertDialog.setButton(androidx.appcompat.app.AlertDialog.BUTTON_NEUTRAL, "Ok",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        return alertDialog;
    }

    public static AlertDialog createDialog(Context context,String message,int theme){
        ImageView viewToAdd = new ImageView(context);

        if(theme == WARNING)
            viewToAdd.setImageResource(R.drawable.error);

        if(theme == SUCCESS)
            viewToAdd.setImageResource(R.drawable.success);

        if(theme == INFO)
            viewToAdd.setImageResource(R.drawable.info);

        AlertDialog alertDialog = createAlert(context,message);
        alertDialog.setTitle(Html.fromHtml("<h1>CoinquiOrganizer</h1>"));
        alertDialog.setView(viewToAdd);

        return alertDialog;
    }


}
