
public class sillyFile {
	private String name;
	private String parent;
	private String path;
	private long lastModifiedTime;
	private long size;
	private int hash;
	public sillyFile(String path1,String parent1,String name1,long time,long size1,int hash1){
		path=path1;
		setParent(parent1);
		setName(name1);
		setLastModifiedTime(time);
		setSize(size1);
		setHash(hash1);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getLastModifiedTime() {
		return lastModifiedTime;
	}
	public void setLastModifiedTime(long lastModifiedTime) {
		this.lastModifiedTime = lastModifiedTime;
	}
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
	public int getHash() {
		return hash;
	}
	public void setHash(int hash) {
		this.hash = hash;
	}
	public String getParent() {
		return parent;
	}
	
	public void setParent(String parent) {
		this.parent = parent;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
}
