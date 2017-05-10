package space.harbour.hw8;

import com.google.gson.*;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BagOfPrimitives obj = new BagOfPrimitives(10);
        System.out.println(obj);

        Gson gson = new Gson();
        String json = gson.toJson(obj);
        System.out.println("\nGOOGLE GSON:\n" + json);

        String myJSON = MyGSON.toJSON(obj);
        System.out.println("\nMy GSON:\n" + myJSON);

        writeBinaryToFile(myJSON);
    }

    public static void writeBinaryToFile(String json) throws IOException {
        byte[] bytes = json.getBytes("UTF-8");
        FileOutputStream fos = new FileOutputStream("sample_json_data.txt");
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        bos.write(bytes);
        bos.flush();
    }
}
