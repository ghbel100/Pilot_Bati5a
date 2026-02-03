package hhu.propra.bati5a.domain.model.topic;

import java.util.Objects;

/**
 * Value object representing Markdown content that can be rendered as HTML.
 * This is a domain concept that encapsulates markdown text.
 */
public class Markdown {
    private final String content;

    public Markdown(String content) {
        this.content = content != null ? content : "";
    }

    /**
     * Returns the raw markdown content.
     */
    public String getContent() {
        return content;
    }

    /**
     * Returns the markdown content (alias for getContent).
     * The actual HTML rendering should be done in the presentation layer.
     */
    public String asMarkdown() {
        return content;
    }

    /**
     * Returns a plain text summary of the content, stripping HTML tags.
     */
    public String getSummary(int maxLength) {
        if (content == null || content.isEmpty()) {
            return "";
        }
        // Naive HTML tag stripping
        String plainText = content.replaceAll("<[^>]*>", " ").replaceAll("\\s+", " ").trim();
        
        if (plainText.length() <= maxLength) {
            return plainText;
        }
        return plainText.substring(0, maxLength) + "...";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Markdown markdown = (Markdown) o;
        return Objects.equals(content, markdown.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content);
    }

    @Override
    public String toString() {
        return content;
    }
}
