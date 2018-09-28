package dev.challenge.boxio.view.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.widget.AppCompatCheckBox;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import dev.challenge.boxio.R;
import dev.challenge.boxio.application.BoxIoApp;
import dev.challenge.boxio.di.components.DaggerScreenComponent;
import dev.challenge.boxio.model.Box;
import dev.challenge.boxio.model.Color;
import dev.challenge.boxio.model.User;
import dev.challenge.boxio.presenters.MainPresenter;
import dev.challenge.boxio.util.Layout;

@Layout(id = R.layout.activity_main)
public class MainActivity extends AbstractActivity implements MainActivityView {

    private final String TAG = getClass().getSimpleName();

    private Box.BoxSize boxSize;
    private Color color;
    private SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy 'at' HH:mm:ss z", Locale.getDefault());

    @BindView(R.id.loading_progress_horizontal)
    ContentLoadingProgressBar progressBar;

    @BindView(R.id.choose_box_size_text_view)
    TextView textViewBoxSize;

    @BindView(R.id.box_size_radio_group)
    RadioGroup radioGroupBoxSize;

    @BindView(R.id.choose_box_color_text_view)
    TextView textViewBoxColor;

    @BindView(R.id.box_color_radio_group)
    RadioGroup radioGroupBoxColor;

    @BindView(R.id.sign_box_check_box)
    AppCompatCheckBox checkBoxSignBox;

    @BindView(R.id.user_mail_edit_text)
    EditText editTextUserMail;

    @BindView(R.id.user_name_edit_text)
    EditText editTextUserName;

    @BindView(R.id.confirm_fab)
    FloatingActionButton confirmFab;

    @Inject
    MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainPresenter.setView(this);

        setupViews();
    }

    private void setupViews() {
        setupRadioGroups();
        setupCheckBox();
        setupFab();
    }

    private void setupRadioGroups() {

        radioGroupBoxSize.setOnCheckedChangeListener((radioGroup, checkedId) -> {
            switch (checkedId) {
                case R.id.small_box_radio:
                    boxSize = Box.BoxSize.Small;
                    break;

                case R.id.medium_box_radio:
                    boxSize = Box.BoxSize.Medium;
                    break;

                case R.id.large_box_radio:
                    boxSize = Box.BoxSize.Large;
                    break;
            }
        });

        radioGroupBoxColor.setOnCheckedChangeListener((radioGroup, checkedId) -> {
            switch (checkedId) {
                case R.id.red_box_radio:
                    color = new Color("Red", "FF0000");
                    break;

                case R.id.orange_box_radio:
                    color = new Color("Orange", "FF8000");
                    break;

                case R.id.yellow_box_radio:
                    color = new Color("Yellow", "FFFF00");
                    break;

                case R.id.green_box_radio:
                    color = new Color("Green", "00FF00");
                    break;

                case R.id.blue_box_radio:
                    color = new Color("Blue", "0000FF");
                    break;

                case R.id.purple_box_radio:
                    color = new Color("Purple", "FF00FF");
                    break;
            }
        });
    }

    private void setupCheckBox() {
        checkBoxSignBox.setOnCheckedChangeListener((compoundButton, isChecked) -> {
            if (isChecked) {
                editTextUserName.setVisibility(View.VISIBLE);
            } else {
                editTextUserName.setVisibility(View.GONE);
            }
        });
    }

    private void setupFab() {
        confirmFab.setOnClickListener(view -> {
            hideKeyboard();
            mainPresenter.submit(new User(editTextUserName.getText().toString(),
                    editTextUserMail.getText().toString(),
                    new Box(boxSize != null ? boxSize.name() : null, color != null ? color : new Color(null, null)),
                    "",
                    format.format(new Date(System.currentTimeMillis())),
                    checkBoxSignBox.isChecked()));
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainPresenter.destroy();
    }

    @Override
    void inject() {
        DaggerScreenComponent.builder()
                .applicationComponent(((BoxIoApp) getApplication()).getApplicationComponent())
                .build().inject(this);
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
        confirmFab.hide();
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
        confirmFab.show();
    }

    @Override
    public void showSuccessMessage(String message) {
        showDialogNotification(message);
    }

    @Override
    public void showErrorMessage(String error) {
        Log.e(TAG, error);
        showDialogNotification(error);
    }

    @Override
    public void onUserProfileSubmitted(JSONObject userJson) {
        System.out.println(userJson);
    }
}


