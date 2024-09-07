@extends('layouts.app')

@section('content')
    <h1 class="mt-4 mb-3">@yield('title')</h1>
    <form action="@yield('form-action')" method="post">
        @csrf
        @yield('form-content')
        <button type="submit" class="btn btn-primary">@yield('button-text')</button>
    </form>
@endsection
