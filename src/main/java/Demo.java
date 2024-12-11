import downloader.SpotifyDownloader;
import some_cool_media_library.SpotifyCacheProxy;
import some_cool_media_library.ThirdPartySpotifyClass;

public class Demo {
    public static void main(String[] args) {
        SpotifyDownloader naiveDownloader = new SpotifyDownloader(new ThirdPartySpotifyClass());
        SpotifyDownloader smartDownloader = new SpotifyDownloader(new SpotifyCacheProxy());

        long naive = test(naiveDownloader);
        long smart = test(smartDownloader);
        System.out.print("Time saved by caching proxy: " + (naive - smart) + "ms");

    }

    private static long test(SpotifyDownloader downloader) {
        long startTime = System.currentTimeMillis();

        // User behavior in our app:
        downloader.renderPopularVideos();
        downloader.renderVideoPage("musica 1");
        downloader.renderPopularVideos();
        downloader.renderVideoPage("musica 2");
        // Users might visit the same page quite often.
        downloader.renderVideoPage("musica 2");
        downloader.renderVideoPage("musica 3");

        long estimatedTime = System.currentTimeMillis() - startTime;
        System.out.print("Time elapsed: " + estimatedTime + "ms\n");
        return estimatedTime;
    }
}