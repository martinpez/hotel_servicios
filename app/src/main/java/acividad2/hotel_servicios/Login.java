package acividad2.hotel_servicios;

import android.database.Cursor;
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
import android.widget.ImageView;
import android.widget.Toast;

import acividad2.hotel_servicios.data.HotelDBHelper;
import acividad2.hotel_servicios.data.Huesped;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Login#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Login extends Fragment implements View.OnClickListener {
    private HotelDBHelper db;
     private Button btn_acc;
     private EditText input_login;
     private EditText input_password;
     private ImageView img_entry;

    private String Errorinicio = "Fill fields";
    private String bienvenida = "Welcome";

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
        return inflater.inflate(R.layout.login, container, false);
    }
    Bundle bundle = new Bundle();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btn_acc = (Button) getActivity().findViewById(R.id.btn_acc);
        btn_acc.setOnClickListener(this);

        input_login = (EditText) getActivity().findViewById(R.id.Input_login);

        input_password = (EditText) getActivity().findViewById(R.id.login_password);

        img_entry = (ImageView) getActivity().findViewById(R.id.img_entry);
        img_entry.setOnClickListener(this);

        db = new HotelDBHelper(getContext());
    }

    @Override
    public void onClick(View view) {
        String email = input_login.getText().toString();
        String password = input_password.getText().toString();
        Cursor cursor = db.getHuespedByUser(email, password);
        if (cursor.moveToNext()){
            Huesped hps = new Huesped(cursor);
            if (view.getId() == img_entry.getId()){
                Bundle bundle1 = new Bundle();
                bundle1.putString("UserNme" , hps.getName());
                Navigation.findNavController( view ).navigate( R.id.cart,bundle1);

            }
        }else
            if (view.getId() == btn_acc.getId()) {
            Navigation.findNavController(view).navigate(R.id.accaunt);

        }else {
            Toast.makeText(getContext(),"Credenciales invalidas",Toast.LENGTH_LONG).show();
        }

    }
}