package beatboxer;
public class Run {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DataBase db = new DataBase();
		db.CreateDataBase();
		AddTrack track = new AddTrack("StarBoy","The Weeknd","StarBoy","Unknown","Pop");
		AddTrack track1 = new AddTrack("False Alarm","The Weeknd","StarBoy","Unknown","Pop");
		track.add();
		track1.add();
		Delete tc = new Delete();
		tc.deleteTrack(2);
		Update up = new Update();
		up.updateTrack(1, "Love Me Harder");
		up.updateArtist(1, "Ariana Grande");
		up.updateAlbum(1, "You Are My Everything");
		
	}

}
