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

import acividad2.hotel_servicios.data.HotelDBHelper;
import acividad2.hotel_servicios.data.Huesped;
import acividad2.hotel_servicios.data.Telefono;
import acividad2.hotel_servicios.data.HuespedContract.HuespedEntry;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Accaunt#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Accaunt extends Fragment implements View.OnClickListener {

    private HotelDBHelper db;
    private ImageView img_entry_acc;

    private EditText input_login , input_phone ,input_password , input_id ,input_name;




    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Accaunt() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Reservation.
     */
    // TODO: Rename and change types and number of parameters
    public static Accaunt newInstance(String param1, String param2) {
        Accaunt fragment = new Accaunt();
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
        return inflater.inflate(R.layout.accaunt, container, false);
    }
    Bundle bundle = new Bundle();
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // EDIT TEXT
        input_login = (EditText) getActivity().findViewById(R.id.input_login2);

        input_phone = (EditText) getActivity().findViewById(R.id.input_phone);

        input_password = (EditText) getActivity().findViewById(R.id.input_phone);

        input_id = (EditText) getActivity().findViewById(R.id.input_phone);


        input_name = (EditText) getActivity().findViewById(R.id.input_name);

        // IMAGE VIEW

        img_entry_acc = (ImageView) getActivity().findViewById(R.id.img_entry_acc);
        img_entry_acc.setOnClickListener(this);

        db = new HotelDBHelper(getContext());
    }

    @Override
    public void onClick(View view){

        //GUARDAR LOS EDITTEXT EN UN STRING
        String nombre = input_name.getText().toString();
        String email = input_login.getText().toString();
        String id = input_id.getText().toString();
        String telephone = input_phone.getText().toString();
        String password = input_password.getText().toString();

        int id_2 = Integer.parseInt(id);
        int phone = Integer.parseInt(telephone);
        Cursor cursor2 = db.getHuespedByUser(email,password);
        if (!cursor2.moveToNext()) {

            Huesped datos = new Huesped(nombre, id_2, email, password);
            Telefono datos2 = new Telefono(id_2, phone);
                        db.saveHuesped(datos, datos2);

        /*if (view.getId() == img_entry_acc.getId()){
            Huesped datos = new Huesped(nombre,id_2,email,password);
            Telefono datos2 = new Telefono(id_2,phone);
            db.saveHuesped(datos,datos2);

            Navigation.findNavController(view).navigate(R.id.login);
        }*/
        }
    }

}