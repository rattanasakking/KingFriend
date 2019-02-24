package nalauchon.in.tooktigfriend;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {


    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        Register Controller
        registerController();
//        Login Controller
        Button button = getView().findViewById(R.id.btnLogin);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText userEditText = getView().findViewById(R.id.edtUser);
                EditText passwordEditText = getView().findViewById(R.id.edtPasswored);

                String user = userEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                MyAlert myAlert = new MyAlert(getActivity());

                if (user.isEmpty()) ||password.isEmpty() {
                    myAlert.normalDialog("Have Space", "Pass Fill All Blank");

                }else{

                    try {

                        GetUserWhereUserThread getUserWhereUserThread = new GetUserWhereUserThread(getActivity());
                        getUserWhereUserThread.execute(user);
                        String json = getUserWhereUserThread.get();
                        Log.d("24FebV1", "json ==>" + json);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }  // Main Method

    private void registerController() {
        TextView textView = getView().findViewById(R.id.txtRegister);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                Replace Fragment
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.contentMainFragment, new RegisterFragment()).addToBackStack(null).commit();
            }
        });
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

}   // Main Class
