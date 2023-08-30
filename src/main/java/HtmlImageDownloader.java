import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.io.File;


public class HtmlImageDownloader {
    public static void main(String[] args) {
        String url = "https://..."; // Замените на URL нужной HTML-страницы
        try {
            Document doc = Jsoup.connect(url).get();
            Elements imgElements = doc.select("img"); // Найти все теги <img>

            String savePath = "D:\\img"; // Замените на свой путь

            for (Element img : imgElements) {
                String imgUrl = img.absUrl("src"); // Получаю абсолютный URL изображения
                downloadImage(imgUrl, savePath); // Скачать изображение
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void downloadImage(String imgUrl, String savePath) throws IOException {
        URL url = new URL(imgUrl);
        String fileName = imgUrl.substring(imgUrl.lastIndexOf('/') + 1); // извлекаю имя файла изображения из урла
        String filePath = savePath + File.separator + fileName; // создаю полный путь к файлу, куда сохранятся изображения
        try (InputStream in = url.openStream(); // создаю потоки для чтения
             FileOutputStream out = new FileOutputStream(filePath)) { // и записи
            byte[] buffer = new byte[1024]; // буфер для чтения и записи файлов
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
        }
    }
}
