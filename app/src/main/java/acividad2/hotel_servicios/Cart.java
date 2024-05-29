package acividad2.hotel_servicios;

import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import acividad2.hotel_servicios.data.CartContract;
import acividad2.hotel_servicios.data.HotelDBHelper;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Cart#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Cart extends Fragment  {
    //variables privasdas
    private Button btn_add;
    private TextView txt_nombre_fit, price_1, price_2, price_3, price_4, price_5, price_6, price_7;
    private CheckBox rad_gui, rad_recre, rad_special, rad_vip, rad_gift, rad_room,rad_mass;
    private ImageView image_Button_User;
    private HotelDBHelper db;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String nombre_use;



    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Cart.
     */
    // TODO: Rename and change types and number of parameters
    public static Cart newInstance(String param1, String param2) {
        Cart fragment = new Cart();
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
        return inflater.inflate(R.layout.cart, container, false);
    }

    Bundle bundle = new Bundle();
    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

        db = new HotelDBHelper(getContext());

        // IMAGESBUTTON
        image_Button_User = getActivity().findViewById(R.id.image_Button_User);


        // ALL TEXTVIEW
        txt_nombre_fit = (TextView) getActivity().findViewById(R.id.txt_nombre_fit);
        price_1 = (TextView) getActivity().findViewById(R.id.price_1);
        price_2 = (TextView) getActivity().findViewById(R.id.price_2);
        price_3 = (TextView) getActivity().findViewById(R.id.price_3);
        price_4 = (TextView) getActivity().findViewById(R.id.price_4);
        price_5 = (TextView) getActivity().findViewById(R.id.price_5);
        price_6 = (TextView) getActivity().findViewById(R.id.price_6);
        price_7 = (TextView) getActivity().findViewById(R.id.price_7);

        // All Buttom
        btn_add = (Button) getActivity().findViewById(R.id.btn_add);

        // ALL CHECKBOX
        rad_gui = (CheckBox) getActivity().findViewById(R.id.rad_gui);
        rad_recre = (CheckBox) getActivity().findViewById(R.id.rad_recre);
        rad_special= (CheckBox) getActivity().findViewById(R.id.rad_special);
        rad_vip= (CheckBox) getActivity().findViewById(R.id.rad_vip);
        rad_gift= (CheckBox) getActivity().findViewById(R.id.rad_gift);
        rad_room= (CheckBox) getActivity().findViewById(R.id.rad_room);
        rad_mass = (CheckBox) getActivity().findViewById(R.id.rad_mass);

        // INSERTS DE TODOS LOS BUNBLE DE CART
        nombre_use =  getArguments().getString("name");
        txt_nombre_fit.setText(nombre_use);
        bundle.putString("name_acc" ,getArguments().getString("name"));
        // obtine el email del cliente desde el Login
        String email = getArguments().getString("email_user");
        bundle.putString("email_u" , email);


        // LOGICA DE LOS CHECK BOX


        rad_room.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean ischek) {
                //  revisa que el checkbox esta CHEQUIADO y con la clase bundle envia (put) el dato del texto
                //  y con el remove elimina con la llave del put, cuando le quite el check   :)
                if (ischek) {
                    bundle.putString("rad_room", rad_room.getText().toString());
                } else {
                    bundle.remove("rad_room");
                }
            }
        });

        rad_gui.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean ischek) {
                //  revisa que el checkbox esta chekeado y con la clase bundle envia (put) el dato del texto
                //  y con el remove elimina con la llave del put, cuando le quite el check   :)
                if (ischek) {
                    bundle.putString("rad_gui", rad_gui.getText().toString());
                } else {
                    bundle.remove("rad_gui");
                }
            }
        });
        rad_gift.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean ischek) {
                //  revisa que el checkbox esta chekeado y con la clase bundle envia (put) el dato del texto
                //  y con el remove elimina con la llave del put, cuando le quite el check   :)
                if (ischek) {
                    bundle.putString("rad_gift", rad_gift.getText().toString());
                } else {
                    bundle.remove("rad_gift");
                }
            }
        });
        rad_vip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean ischek) {
                //  revisa que el checkbox esta chekeado y con la clase bundle envia (put) el dato del texto
                //  y con el remove elimina con la llave del put, cuando le quite el check   :)
                if (ischek) {
                    bundle.putString("rad_vip", rad_vip.getText().toString());
                } else {
                    bundle.remove("rad_vip");
                }
            }
        });
        rad_recre.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean ischek) {
                //  revisa que el checkbox esta chekeado y con la clase bundle envia (put) el dato del texto
                //  y con el remove elimina con la llave del put, cuando le quite el check   :)
                if (ischek) {
                    bundle.putString("rad_recre", rad_recre.getText().toString());
                } else {
                    bundle.remove("rad_recre");
                }
            }
        });
        rad_special.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean ischek) {
                //  revisa que el checkbox esta chekeado y con la clase bundle envia (put) el dato del texto
                //  y con el remove elimina con la llave del put, cuando le quite el check   :)
                if (ischek) {
                    bundle.putString("rad_special", rad_special.getText().toString());
                } else {
                    bundle.remove("rad_special");
                }
            }
        });
        rad_mass.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean ischek) {
                //  revisa que el checkbox esta chekeado y con la clase bundle envia (put) el dato del texto
                //  y con el remove elimina con la llave del put, cuando le quite el check   :)
                if (ischek) {
                    bundle.putString("rad_mass", rad_mass.getText().toString());
                } else {
                    bundle.remove("rad_mass");
                }
            }
        });
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId()== btn_add.getId()){
                    Navigation.findNavController(view).navigate(R.id.buy, bundle);
                    Toast.makeText(getContext() , "Usa el codigo 200 para \n Usarios nuevos  descuentos !",Toast.LENGTH_LONG).show();
                }
            }
        });

        // ONCLIK DEL IMAGESBUTTON PARA EL POPUPMENU
        image_Button_User.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // llama al metodo popup
                popupmenu();

            }
        });

        setItemPrices();

    }

        // logica de la database cart
    private void setItemPrices() {
        Cursor cursor = db.getAllItems();

        if (cursor != null && cursor.moveToFirst()) {
            do {
                String item_name = cursor.getString(cursor.getColumnIndexOrThrow(CartContract.CartEntry.COLUMN_NAME));
                String item_price = cursor.getString(cursor.getColumnIndexOrThrow(CartContract.CartEntry.COLUMN_ITEM_PRICE));

                System.out.println("nombre " + item_name);
                System.out.println("precio: " + item_price);



                // Configura los precios en los EditText correspondientes

                if (rad_room.getText().toString().equals(item_name)) {
                    price_1.setText(item_price);
                } else if (rad_gui.getText().toString().equals(item_name)) {
                    price_2.setText(item_price);
                } else if (rad_gift.getText().toString().equals(item_name)){
                    price_3.setText(item_price);
                } else if (rad_vip.getText().toString().equals(item_name)) {
                    price_4.setText(item_price);
                } else if (rad_recre.getText().toString().equals(item_name)) {
                    price_5.setText(item_price);
                } else if (rad_special.getText().toString().equals(item_name)) {
                    price_6.setText(item_price);
                } else if (rad_mass.getText().toString().equals(item_name)) {
                    price_7.setText(item_price);
                }

            } while (cursor.moveToNext());

            cursor.close();
        }
    }

    // LOGICA DEL POPUPMENU
        private void  popupmenu(){
            PopupMenu p = new PopupMenu(getContext() , image_Button_User);

            p.getMenuInflater().inflate(R.menu.popmenu ,p.getMenu() );
            p.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    Toast.makeText(getContext() , "coming soon ;)", Toast.LENGTH_LONG).show();
                    return false;
                }
            });
            p.show();
        }

    }