package vn.asiantech.LearingEnglish.utils;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import vn.asiantech.LearingEnglish.R;

/**
 * Created by thehoa on 8/31/15.
 */
public class Utils {
    public static void callDialogForgetPass(final Context context) {
        final Dialog dialog = new Dialog(context, R.style.DialogForgetPassword);
        dialog.setContentView(R.layout.dialog_forget_password);
        dialog.show();
        final EditText edtEmail = (EditText) dialog.findViewById(R.id.edtEmail);
        Button btnForgetPassPass = (Button) dialog.findViewById(R.id.btnForgetPass);
        btnForgetPassPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Utils.isValidEmail(edtEmail.getText())) {
                    Toast.makeText(context, "successfully", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                } else {
                    Toast.makeText(context, "Invalid email", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private static boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }
}
