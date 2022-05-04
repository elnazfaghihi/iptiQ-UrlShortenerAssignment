import api.UrlShortener;
import model.UrlDTO;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        UrlShortener urlShortener = new UrlShortener();
        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.println("Choose url shortener method");
            System.out.println("1.Generate url randomly");
            System.out.println("2.Seo keyword");
            System.out.println("3.Retrieve original url");
            System.out.println("4.Exit");

            int n = scanner.nextInt();
            if (n == 1) {
                System.out.println("Enter original url");
                String originalUrl = scanner.next();

                System.out.println("Enter url validity seconds");
                Integer tokenValiditySeconds = scanner.nextInt();

                String shortenUrl = urlShortener.generateShortenUrlRandomly(new UrlDTO(originalUrl, tokenValiditySeconds));
                System.out.println("Shorten url is :" + shortenUrl);
            } else if (n == 2) {
                System.out.println("Enter original url");
                String originalUrl = scanner.next();

                System.out.println("Enter seo keyword");
                String seoKeyword = scanner.next();

                System.out.println("Enter url validity seconds");
                Integer tokenValiditySeconds = scanner.nextInt();

                String shortenUrl = urlShortener.generateShortenUrlBySeoKeyword(new UrlDTO(originalUrl, seoKeyword, tokenValiditySeconds));
                System.out.println("Shorten url is : " + shortenUrl);
            }  else if (n == 3) {
                System.out.println("Enter shorten url");
                String shortenUrl = scanner.next();
                System.out.println("Shorten url is : " + urlShortener.findOriginalUrl(shortenUrl));
            } else {
                break;
            }
        }
    }
}
