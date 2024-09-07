<?php

// tests/Feature/TaskManagementTest.php

namespace Tests\Feature;

use Illuminate\Foundation\Testing\RefreshDatabase;
use Illuminate\Foundation\Testing\WithFaker;
use Tests\TestCase;
use App\Models\Task;

class TaskManagementTest extends TestCase
{
    use RefreshDatabase;

    /** @test */
    public function user_can_view_tasks()
    {
        $response = $this->get('/');
        $response->assertStatus(200);
    }

    /** @test */
    public function user_can_create_task()
    {
        $response = $this->post('/tasks', [
            'title' => 'New Task',
            'description' => 'Description of the new task'
        ]);
        $this->assertDatabaseHas('tasks', ['title' => 'New Task']);
        $response->assertRedirect('/');
    }

    /** @test */
    public function user_can_update_task()
    {
        $task = Task::factory()->create();
        $response = $this->put('/tasks/' . $task->id, [
            'title' => 'Updated Task',
            'description' => 'Updated description of the task'
        ]);
        $this->assertDatabaseHas('tasks', ['title' => 'Updated Task']);
        $response->assertRedirect('/');
    }

    /** @test */
    public function user_can_delete_task()
    {
        $task = Task::factory()->create();
        $response = $this->delete('/tasks/' . $task->id);
        $this->assertDeleted($task);
        $response->assertRedirect('/');
    }
}
