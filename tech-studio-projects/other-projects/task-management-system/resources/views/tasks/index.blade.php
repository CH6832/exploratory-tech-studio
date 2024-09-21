@extends('layouts.app')

@section('content')
    <h1 class="mt-4 mb-3">List of Tasks</h1>
    <ul class="list-group">
        @foreach($tasks as $task)
            <li class="list-group-item">
                <div class="d-flex justify-content-between">
                    <div>
                        <h5>{{ $task['title'] }}</h5>
                        <p class="mb-1">{{ $task['description'] }}</p>
                    </div>
                    <form action="/tasks/{{ $task['id'] }}" method="post">
                        @csrf
                        @method('DELETE')
                        <button type="submit" class="btn btn-danger">Delete</button>
                    </form>
                </div>
            </li>
        @endforeach
    </ul>
    <a href="/tasks/create" class="btn btn-primary mt-3">Add New Task</a>
@endsection
