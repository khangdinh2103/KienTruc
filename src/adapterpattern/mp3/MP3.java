package adapterpattern.mp3;

interface MediaPlayer {
    public void play(String type, String fileName);
}
class AdvancedMedia {
    public void playMp3(String FileName) {
        System.out.println("Playing mp3" + FileName);
    }
    public void playFlac(String FileName) {
        System.out.println("Playing plac" + FileName);
    }
}

class AdapterMedia implements MediaPlayer {

    AdvancedMedia advancedMedia = new AdvancedMedia();
    public AdapterMedia() {
        advancedMedia = new AdvancedMedia();
    }
    @Override
    public void play(String type, String fileName) {
        if (type.equals("mp3")) {
            advancedMedia.playMp3(fileName);
        }
        else if (type.equals("flac")) {
            advancedMedia.playFlac(fileName);
        }

    }
}

public class MP3 {
    public static void main(String[] args) {
        MediaPlayer mediaPlayer = new AdapterMedia();
        mediaPlayer.play("mp3", "hehe");
    }
}
