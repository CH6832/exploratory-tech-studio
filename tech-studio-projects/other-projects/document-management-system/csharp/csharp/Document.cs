namespace Document
{
    public class Document
    {
        public string Title { get; }
        public string Content { get; }

        public Document(string title, string content)
        {
            Title = title;
            Content = content;
        }

        public override string ToString()
        {
            return $"Title: {Title}\nContent: {Content}";
        }
    }
}
