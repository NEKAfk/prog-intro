package md2html;

public class Text implements ParagraphElem {
    protected final String text;
    public Text(String text) {
        this.text = text;
    }

    @Override
    public void toHtml(StringBuilder sb) {
        for (int i = 0; i < text.length(); i++) {
            switch (text.charAt(i)) {
                case '<':
                    sb.append("&lt;");
                    break;
                case '>':
                    sb.append("&gt;");
                    break;
                case '&':
                    sb.append("&amp;");
                    break;
                default:
                    sb.append(text.charAt(i));
                    break;
            }
        }
    }
}