package acividad2.hotel_servicios;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Login#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Login extends Fragment implements View.OnClickListener{

     private Button btnlinear;
     private EditText input_name;
     private EditText input_login;
     private EditText input_password;

    private String Errorinicio = "Fill fields";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Login() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Login.
     */
    // TODO: Rename and change types and number of parameters
    public static Login newInstance(String param1, String param2) {
        Login fragment = new Login();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.linear, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnlinear = (Button) getActivity().findViewById(R.id.btnlinear);
        btnlinear.setOnClickListener(this);

        input_name = (EditText) getActivity().findViewById(R.id.input_name);
        input_name.setOnClickListener(this);

        input_login = (EditText) getActivity().findViewById(R.id.input_login);
                input_login.setOnClickListener(this);

        input_password  = (EditText) getActivity().findViewById(R.id.input_password);
        input_password.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (!input_name.getText().toString().equals("") & !input_login.getText().toString().equals("") & !input_password.getText().toString().equals("")  ) {
            Navigation.findNavController(view).navigate(R.id.reservation);
        } else {
            input_name.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
            input_password.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
            input_login.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
            Toast.makeText(getContext(), Errorinicio, Toast.LENGTH_LONG).show();
        }
    }
}