@import exercise.dto.articles.NewArticlePage
@param NewArticlePage page

@if(page.getErrors() != null)
    <ul>
        @for(var validator: page.getErrors().values())
            @for(var error: validator)
                <li>${error.getMessage()}</li>
            @endfor
        @endfor
    </ul>
@endif

<form action="/articles" method="post">
    <div>
        <label>
            A title for your article:
            <input type="text" name="title" value="${page.getTitle()}">
        </label>
    </div>
    <div>
        <label>
            A content of your article:
            <textarea name="content">${page.getContent()}</textarea>
        </label>
    </div>
    <input type="submit" value="Done">
</form>
