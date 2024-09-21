<?php

// routes/docs.php

use Illuminate\Support\Facades\File;
use League\CommonMark\CommonMarkConverter;

Route::get('/docs/{file}', function ($file) {
    $path = resource_path("docs/{$file}.md");

    if (!File::exists($path)) {
        abort(404);
    }

    $content = File::get($path);
    $converter = new CommonMarkConverter();

    return view('docs', [
        'content' => $converter->convertToHtml($content)
    ]);
});
