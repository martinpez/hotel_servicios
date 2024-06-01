package acividad2.hotel_servicios;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import org.w3c.dom.ls.LSOutput;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import acividad2.hotel_servicios.data.CartContract;
import acividad2.hotel_servicios.data.HotelDBHelper;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Buy#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Buy extends Fragment implements View.OnClickListener {
    double total = 0.0;

    private ImageView button_back;
    private EditText edit_discount;

    private HotelDBHelper db;
    private Button btn_apply_disccount ,btn_reservations;
    private TextView  txt_discount,txt_iva,txt_totalsum,txt_subtotal,txt_fit,txt_precio_3_buy,txt_precio_4_buy,txt_precio_6_buy,txt_precio_7_buy,txt_precio_8_buy,txt_precio_9_buy,txt_precio_10_buy;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private int suma_total;

    private String nombre_user ;
    private List<Integer> selectedItemIds;


    public Buy() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Buy.
     */
    // TODO: Rename and change types and number of parameters
    public static Buy newInstance(String param1, String param2) {
        Buy fragment = new Buy();
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
    Bundle bundle = new Bundle();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.buy, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        db = new HotelDBHelper(getContext());

        // edit txt
        edit_discount = (EditText) getActivity().findViewById(R.id.edit_discount);

        // ALL BUTTON

        button_back= (ImageView) getActivity().findViewById(R.id.button_back);
        btn_apply_disccount = (Button) getActivity().findViewById(R.id.btn_apply_disccount);
        btn_reservations = (Button) getActivity().findViewById(R.id.btn_reservations);

        // ALL BUTTON this for implements
        button_back.setOnClickListener(this);
        btn_reservations.setOnClickListener(this);


        // ALL TEXTVIEW

        txt_precio_3_buy = (TextView) getActivity().findViewById(R.id.txt_precio_3_buy);
        txt_precio_4_buy= (TextView) getActivity().findViewById(R.id.txt_precio_4_buy);
        txt_precio_6_buy= (TextView) getActivity().findViewById(R.id.txt_precio_6_buy);
        txt_precio_7_buy= (TextView) getActivity().findViewById(R.id.txt_precio_7_buy);
        txt_precio_8_buy= (TextView) getActivity().findViewById(R.id.txt_precio_8_buy);
        txt_precio_9_buy= (TextView) getActivity().findViewById(R.id.txt_precio_9_buy);
        txt_precio_10_buy= (TextView) getActivity().findViewById(R.id.txt_precio_10_buy);
        txt_fit = (TextView)  getActivity().findViewById(R.id.txt_fit);
        txt_discount = (TextView) getActivity().findViewById(R.id.txt_discount);
        txt_iva= (TextView) getActivity().findViewById(R.id.txt_iva);
        txt_totalsum= (TextView) getActivity().findViewById(R.id.txt_totalsum);
        txt_subtotal= (TextView) getActivity().findViewById(R.id.txt_subtotal);
        // INSERTS DE TODOS LOS BUNBLE DE CART

        txt_fit.setText( getArguments().getString("name_acc"));


        nombre_user = getArguments().getString("name_acc");
        bundle.putString("name" , getArguments().getString("name_acc"));



        // Obtiene el texto de los TextViews y los convierte a números

        setItemPrices_buy();
        btn_apply_disccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                applyDiscount();
                edit_discount.setText("");
                dismissKeyboard(view);
                Toast.makeText(getContext(), "Descuento aplicado " , Toast.LENGTH_LONG).show();
            }
        });

    }

    private void setItemPrices_buy() {
        String rad_room = getArguments().getString("rad_room");
        String rad_gui = getArguments().getString("rad_gui");
        String rad_gift = getArguments().getString("rad_gift");
        String rad_vip = getArguments().getString("rad_vip");
        String rad_recre = getArguments().getString("rad_recre");
        String rad_special = getArguments().getString("rad_special");
        String rad_mass = getArguments().getString("rad_mass");
        String cath_null = "notselect";

        selectedItemIds = new ArrayList<>();




        System.out.println("Total suma " + total);


        if (rad_room == null) {
            rad_room = cath_null;
        }
        if (rad_gui == null) {
            rad_gui = cath_null;
        }
        if (rad_gift == null) {
            rad_gift = cath_null;
        }
        if (rad_vip == null) {
            rad_vip = cath_null;
        }
        if (rad_recre == null) {
            rad_recre = cath_null;
        }
        if (rad_special == null){
            rad_special = cath_null;
        }
        if (rad_mass == null) {
            rad_mass = cath_null;
        }



        Cursor cursor = db.getAllItems();
        if (cursor != null && cursor.moveToFirst()) {
            do {
                String item_name = cursor.getString(cursor.getColumnIndexOrThrow(CartContract.CartEntry.COLUMN_NAME));
                String item_price = cursor.getString(cursor.getColumnIndexOrThrow(CartContract.CartEntry.COLUMN_ITEM_PRICE));
                int itemId = cursor.getInt(cursor.getColumnIndexOrThrow(CartContract.CartEntry._ID));

                System.out.println("nombre " + item_name);
                System.out.println("precio: " + item_price);

                double price = parsePrice(item_price);

                // Configura los precios en los EditText correspondientes

                if (rad_room.equals(item_name)) {
                    txt_precio_3_buy.setText(item_price);
                    total += price;
                    selectedItemIds.add(itemId);
                } else {
                    //   txt_precio_3_buy.setText(defautValue);
                }
                if (rad_gui.equals(item_name)) {
                    txt_precio_4_buy.setText(item_price);
                    total += price;
                    selectedItemIds.add(itemId);
                } else {
                    //      txt_precio_4_buy.setText(defautValue);
                }
                if (rad_gift.equals(item_name)) {
                    txt_precio_6_buy.setText(item_price);
                    total += price;
                    selectedItemIds.add(itemId);
                } else {
                    //  txt_precio_6_buy.setText(defautValue);
                }
                if (rad_vip.equals(item_name)) {
                    txt_precio_7_buy.setText(item_price);
                    total += price;
                    selectedItemIds.add(itemId);
                }else {
                    //    txt_precio_7_buy.setText(defautValue);
                }
                if (rad_recre.equals(item_name)) {
                    txt_precio_8_buy.setText(item_price);
                    total += price;
                    selectedItemIds.add(itemId);
                } else {
                    // txt_precio_8_buy.setText(defautValue);
                }
                if (rad_special.equals(item_name)){
                    txt_precio_9_buy.setText(item_price);
                    total += price;
                    selectedItemIds.add(itemId);
                }else{
                    //   txt_precio_9_buy.setText(defautValue);
                }
                if (rad_mass.equals(item_name)) {
                    txt_precio_10_buy.setText(item_price);
                    total += price;
                    selectedItemIds.add(itemId);
                }else {
                    // txt_precio_10_buy.setText(defautValue);
                }

            } while (cursor.moveToNext());

            cursor.close();

        }
        double iva = total * 0.19;
        double totalConIva = total + iva;

        // Establecer los valores en los TextView correspondientes
        txt_subtotal.setText("$" + formatPrice(total));
        txt_iva.setText("19 % ");
        txt_totalsum.setText("$" + formatPrice(totalConIva));


        txt_subtotal.setText("$" + formatPrice(total));


    }
    // Método para parsear el precio eliminando puntos de miles y dejando el punto decimal
    private double parsePrice(String price) {
        // Eliminar todos los puntos (.) excepto el último punto decimal, si existe
        int lastDotIndex = price.lastIndexOf(".");
        StringBuilder cleanedPrice = new StringBuilder();

        for (int i = 0; i < price.length(); i++) {
            if (price.charAt(i) == '.' && i != lastDotIndex) {
                continue; // Omite los puntos de miles
            }
            cleanedPrice.append(price.charAt(i));
        }

        return Double.parseDouble(cleanedPrice.toString());
    }

    // Método para formatear el precio a un string con separadores de miles
    private String formatPrice(double price) {
        // Formatear el número con separadores de miles y dos decimales
        return String.format("%,.2f", price);
    }

    private void applyDiscount() {

        String discountCode = edit_discount.getText().toString();
        double discountAmount = 0.0;

        // Verificar el código de descuento
        if (discountCode.equals("200")) {
            discountAmount = 200.0;

        }

        // Obtener el total actual
        String totalString = txt_totalsum.getText().toString().replace("$", "").replace(",", "");
        double total = Double.parseDouble(totalString);

        // Aplicar el descuento
        double newTotal = total - discountAmount;

        // Asegurarse de que el nuevo total no sea negativo
        if (newTotal < 0) {
            newTotal = 0;
        }
        txt_discount.setText(String.valueOf(discountAmount));
        // Actualizar el TextView con el nuevo total
        txt_totalsum.setText("$" + formatPrice(newTotal));
    }

    private void dismissKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager)  requireContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
    private void reservar() {
        // Generar un código de reserva aleatorio
        Random random = new Random();
        int codigoReserva = random.nextInt(100000);

        // Obtener el ID del usuario a través de su email
        String userEmail = getArguments().getString("email_u");
        int userId = db.getUserIdByEmail(userEmail);

        System.out.println("Email de cliente : "  + getArguments().getString("email_u"));
        if (userId == -1) {
            Toast.makeText(getContext(), "Error al obtener el ID del usuario", Toast.LENGTH_LONG).show();
            return;
        }


        // Obtener el ID del usuario a través de su Email
        String Telef_user =getArguments().getString("email_u");

        int id_user = db.getColIdByEmail(Telef_user);
        // System.out.println("Telefono de cliente : "  + getArguments().getString("tel"));
        if (id_user == -1) {
            Toast.makeText(getContext(), "Error al obtener el ID del usuario mediante el telefono", Toast.LENGTH_LONG).show();
            return;
        }

        // Obtener el ID del usuario a través de su Telefono

        for (int itemId : selectedItemIds) {
            System.out.println("codigo de reserva : " + codigoReserva + " User_id " + userId + " Id_user " + id_user + " id_Item " + itemId);
            db.insertReservation(codigoReserva, userId, id_user, itemId);
            // Toast.makeText(getContext(), "Reserva realizada con éxito", Toast.LENGTH_LONG).show();
        }
        // Insertar la reserva en la base de datos

        // Mostrar un mensaje de confirmación al usuario

    }


    @Override
    public void onClick(View view) {
        if (view.getId() == btn_reservations.getId()){
            reservar();
            Navigation.findNavController(view).navigate(R.id.view_reservation);
        } else if (view.getId() == button_back.getId()) {

            Navigation.findNavController(view).navigate(R.id.cart,bundle);
        }
    }
}