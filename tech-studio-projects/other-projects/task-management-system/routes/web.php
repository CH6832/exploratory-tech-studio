<?php

use Illuminate\Support\Facades\Route;
use App\Http\Controllers\TaskController;


/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| contains the "web" middleware group. Now create something great!
|
*/

Route::get('/', function () {
    return view('welcome');
});

Route::get('/', [TaskController::class, 'index']);
Route::get('/tasks/create', [TaskController::class, 'create']); // Add this line
Route::post('/tasks', [TaskController::class, 'store']);
Route::get('/tasks/{id}/edit', [TaskController::class, 'edit']); // Add this line
Route::put('/tasks/{id}', [TaskController::class, 'update']);
Route::delete('/tasks/{id}', [TaskController::class, 'destroy']);

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