package acividad2.hotel_servicios;


import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteOpenHelper;
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

import java.util.Objects;

import acividad2.hotel_servicios.data.HotelDBHelper;
import acividad2.hotel_servicios.data.Huesped;
import acividad2.hotel_servicios.data.Telefono;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Login#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Login extends Fragment implements View.OnClickListener {


    private HotelDBHelper db;
    private Button btn_acc;
    private EditText login_password, login_email;
    private ImageView img_entry;

    String telf = "";

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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        // edit text
        login_password = (EditText) getActivity().findViewById(R.id.login_password);
        login_email = (EditText)  getActivity().findViewById(R.id.login_email);

        // Images
        img_entry = (ImageView) getActivity().findViewById(R.id.img_entry);
        img_entry.setOnClickListener(this);

        // Buttom
        btn_acc = (Button) getActivity().findViewById(R.id.btn_acc);
        btn_acc.setOnClickListener(this);

        db = new HotelDBHelper(getContext());

        // bundle


    }


    @Override
    public void onClick(View view) {
        String email = login_email.getText().toString();
        String password = login_password.getText().toString();
        Cursor cursor = db.getHuespedByUser(email, password);

        if (view.getId() == img_entry.getId()) {
            if (cursor != null && cursor.moveToFirst()){
                do {
                    Huesped hps = new Huesped(cursor);
                    Bundle bundle = new Bundle();
                    bundle.putString("email_user", hps.getEmail_huesped());
                    bundle.putString("name", hps.getName());
                    Navigation.findNavController(view).navigate(R.id.cart, bundle);
                } while (cursor.moveToNext());
            } else {
                Toast.makeText(getContext(),"Credenciales inválidas",Toast.LENGTH_LONG).show();
            }
        } else if (view.getId() == btn_acc.getId()) {
            if (!email.isEmpty() && !password.isEmpty()) {
                if (cursor != null && cursor.moveToFirst()) {
                    Toast.makeText(getContext(), "Iniciando sesión...", Toast.LENGTH_SHORT).show();
                    // Aquí podrías realizar la lógica de inicio de sesión, si es necesario
                } else {
                    // Si los campos no están vacíos pero las credenciales son inválidas, puedes mostrar un mensaje de error
                    Toast.makeText(getContext(), "Credenciales inválidas", Toast.LENGTH_SHORT).show();
                }
            } else {
                // Si los campos están vacíos, navega directamente al fragmento de creación de cuenta
                Navigation.findNavController(view).navigate(R.id.accaunt);
            }
        }
    }

}
