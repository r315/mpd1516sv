package html;

import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Consumer;

public class HtmlTree {
	
	protected HtmlElement root;

	public static HtmlElement title(String text){
		return new HtmlElement("title",text);
	}
	public static HtmlElement style(String style){
		return new HtmlElement("style",style);
	}
	public static HtmlElement heading(String h, String text){
		return new HtmlElement(h,text);
	}
	public static HtmlElement form(){
		return new HtmlElement("form");
	}
	public static HtmlElement div(String id){
		return new HtmlElement("div").addAttributes("id",id);
	}
	public static HtmlElement p() {return new HtmlElement("p");}

	public String getHtml(){
		StringBuilder sb = new StringBuilder("<!DOCTYPE html>\n");
		getHtml(sb,root, 0);
		return  sb.toString();
	}

	public HtmlTree(){
		root = new HtmlElement("html")
				.addChild(new HtmlElement("head")
					.addChild(style("table, th, td { border: 1px solid black; border-collapse: collapse;}")))
				.addChild(new HtmlElement("body")
						.addChild(div("header")));
	}

	private void addElementTo(String tag, HtmlElement htmlElement, Consumer<HtmlElement> cons) {
		HtmlElement element = findElementByTag(tag);
		if(element != null)
			cons.accept(element);
	}

	public void addElementTo(String tag, HtmlElement htmlElement) {
		addElementTo(tag,htmlElement,e -> e.addChild(htmlElement));
	}

	public void addElementTo(String tag, HtmlElement htmlElement, int pos) {
		addElementTo(tag,htmlElement,e -> e.addChild(htmlElement,pos));
	}

	public void addLinkToContent(String content, String link){
		HtmlElement elm = findByContent(content);
		if(elm != null)
			elm.addLink(link);
	}

	public HtmlElement findElementByTag(String ttag){
		return findElement(root, ttag, (k, r) -> r.getTag().equals(k));
	}

	public HtmlElement findByContent(String cont) {
		return findElement(root, cont, (k, r) -> r.getContent().equals(k));
	}

	public HtmlElement findById(String id) { //used to search div element
		return findElement(root, id, (k, r) -> r.getAttributes().contains("id=" + "\"" + k + "\""));
	}

	private HtmlElement findElement(HtmlElement root, String key, BiPredicate<String,HtmlElement> pred){
		if(pred.test(key,root))
			return root;
		for(HtmlElement child : root.getChilds()){
			HtmlElement next = findElement(child, key, pred);
			if( next != null)
				return next;
		}
		return null;
	}

	private void getHtml(StringBuilder sb, HtmlElement root, int level){
		StringBuilder tabs = new StringBuilder("\n");
		String content = root.getContent();
		String tag = root.getTag();
		String attributes = root.getAttributes();

		for(int i = 0; i < level; i++) {
			tabs.append("\t");
		}

		sb.append(tabs + "<" + tag + attributes + ">");

		for(HtmlElement child : root.getChilds()){
			getHtml(sb, child, level + 1);
		}

		sb.append(content.length()!=0?content:tabs);
		sb.append("</" + tag + ">");
	}


	public static HtmlElement createTable(){
		HtmlElement table = new HtmlElement("table")
			.addAttributes("width", "100%")
			.addAttributes("border", "1")
			.addAttributes("style", "border-collapse:collapse;");
		return table;
	}

	public HtmlElement addElementToDiv(String div, HtmlElement elem) {
		return findById(div).addChild(elem);
	}


}
