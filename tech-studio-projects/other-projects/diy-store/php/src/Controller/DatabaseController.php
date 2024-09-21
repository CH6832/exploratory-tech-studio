<?php

// src/Controller/DatabaseController.php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Doctrine\DBAL\Connection;

class DatabaseController extends AbstractController
{
    private $connection;

    public function __construct(Connection $connection)
    {
        $this->connection = $connection;
    }

    #[Route('/database', name: 'database')]
    public function index(): Response
    {
        $sql = 'SELECT * FROM some_table';
        $stmt = $this->connection->query($sql);
        $results = $stmt->fetchAllAssociative();

        return $this->render('database/index.html.twig', [
            'results' => $results,
        ]);
    }
}

?>