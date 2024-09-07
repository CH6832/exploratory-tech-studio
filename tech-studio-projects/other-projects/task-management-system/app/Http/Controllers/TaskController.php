<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;

class TaskController extends Controller
{
    private $filePath;

    public function __construct()
    {
        $this->filePath = storage_path('app/tasks.json');
    }

    public function index()
    {
        $tasks = $this->getTasks();
        return view('tasks.index', compact('tasks'));
    }

    public function store(Request $request)
    {
        $tasks = $this->getTasks();

        $newTask = [
            'id' => uniqid(),
            'title' => $request->input('title'),
            'description' => $request->input('description')
        ];

        $tasks[] = $newTask;
        $this->saveTasks($tasks);

        return redirect('/');
    }

    public function create()
    {
        return view('tasks.create');
    }

    public function update(Request $request, $id)
    {
        $tasks = $this->getTasks();

        foreach ($tasks as &$task) {
            if ($task['id'] == $id) {
                $task['title'] = $request->input('title');
                $task['description'] = $request->input('description');
                break;
            }
        }

        $this->saveTasks($tasks);

        return redirect('/');
    }

    public function destroy($id)
    {
        $tasks = $this->getTasks();

        $tasks = array_filter($tasks, function ($task) use ($id) {
            return $task['id'] != $id;
        });

        $this->saveTasks($tasks);

        return redirect('/');
    }

    private function getTasks()
    {
        if (!file_exists($this->filePath)) {
            return [];
        }

        $content = file_get_contents($this->filePath);
        return json_decode($content, true) ?: [];
    }

    private function saveTasks($tasks)
    {
        file_put_contents($this->filePath, json_encode($tasks, JSON_PRETTY_PRINT));
    }
}
