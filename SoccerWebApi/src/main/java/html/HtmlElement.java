package html;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class HtmlElement{
	public static final int FIRST_ELEMENT = 1 ;
	public static final int SECOND_ELEMENT = 1 ;
	private String tag = "";
	private String content =""; 
	private String attributes = "";
	private Collection<HtmlElement> childs;

	public HtmlElement(String tag){
		this.tag = tag;
		childs = new ArrayList<HtmlElement>();
	}
	public HtmlElement(String tag, String content){
		this(tag);
		this.content = content;
	}

    public void addLink(String link){
        content = "<a href = \"" + link + "\" > " + content + "</a>";
    }
    public void addContent(String content){	this.content = content;}

	public HtmlElement addAttributes(String atr, String val){
		this.attributes += " " + (val != null ? atr + "=\"" + val +"\"" : atr);
		return this;
	}

	public HtmlElement addChild(HtmlElement node){
		childs.add(node);
		return this;
	}
    public void addChild(HtmlElement node,int pos){
        List<HtmlElement> left = childs.stream().limit(pos).collect(Collectors.toList());
        left.add(node);
        left.addAll(childs.stream().skip(pos).collect(Collectors.toList()));
        childs = left;
    }
    public Collection<HtmlElement> getChilds(){ return childs; }
    public String getTag(){ return tag;}
    public String getContent(){ return content;}
    public String getAttributes(){ return attributes;}
}
