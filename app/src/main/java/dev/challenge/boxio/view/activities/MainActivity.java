package dev.challenge.boxio.view.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.AppCompatCheckBox;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import dev.challenge.boxio.R;
import dev.challenge.boxio.application.BoxIoApp;
import dev.challenge.boxio.di.components.DaggerScreenComponent;
import dev.challenge.boxio.model.BoxEntity;
import dev.challenge.boxio.model.ColorEntity;
import dev.challenge.boxio.model.UserEntity;
import dev.challenge.boxio.model.room.dao.BoxDao;
import dev.challenge.boxio.model.room.dao.ColorDao;
import dev.challenge.boxio.model.room.dao.UserDao;
import dev.challenge.boxio.util.Layout;
import dev.challenge.boxio.util.SharedPreferencesManager;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

@Layout(id = R.layout.activity_main)
public class MainActivity extends AbstractActivity {

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
    SharedPreferencesManager sharedPreferencesManager;

    @Inject
    UserDao userDao;

    @Inject
    BoxDao boxDao;

    @Inject
    ColorDao colorDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setupViews();

        UserEntity userEntity =
                new UserEntity("Jumanji",
                        "jumanji@gmail.com",
                        new BoxEntity(BoxEntity.BoxSize.Medium.name(),
                                new ColorEntity("Black", "000000")));

        new CompositeDisposable().add(Observable.fromCallable(() -> {
            userDao.insertUser(userEntity);
            return userEntity;
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(user -> System.out.println("Inserted user: " + user)));

        new CompositeDisposable().add(userDao.getUsers().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(users -> System.out.println("Users from DB: " + users)));
    }

    private void setupViews() {
        setupRadioGroups();
        setupCheckBox();
    }

    private void setupRadioGroups() {

        radioGroupBoxSize.setOnCheckedChangeListener((radioGroup, checkedId) -> {
            switch (checkedId) {
                case R.id.small_box_radio:
                    showToast("Small");
                    break;

                case R.id.medium_box_radio:
                    showToast("Medium");
                    break;

                case R.id.large_box_radio:
                    showToast("Large");
                    break;

                default:
                    break;
            }
        });

        radioGroupBoxColor.setOnCheckedChangeListener((radioGroup, checkedId) -> {
            switch (checkedId) {
                case R.id.red_box_radio:
                    showToast("Red");
                    break;

                case R.id.orange_box_radio:
                    showToast("Orange");
                    break;

                case R.id.yellow_box_radio:
                    showToast("Yellow");
                    break;

                case R.id.green_box_radio:
                    showToast("Green");
                    break;

                case R.id.blue_box_radio:
                    showToast("Blue");
                    break;

                case R.id.purple_box_radio:
                    showToast("Purple");
                    break;

                default:
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

    @Override
    void inject() {
        DaggerScreenComponent.builder()
                .applicationComponent(((BoxIoApp) getApplication()).getApplicationComponent())
                .build().inject(this);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showErrorMessage(String error) {

    }
}
