<?php

// src/Controller/LoginController.php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Doctrine\DBAL\Connection; // DBAL Connection for raw SQL queries
use Symfony\Component\HttpFoundation\Session\SessionInterface;

class LoginController extends AbstractController
{
    #[Route('/login', name: 'login')]
    public function login(Request $request, Connection $connection, SessionInterface $session): Response
    {
        if ($request->isMethod('POST')) {
            // Get POST data
            $email = $request->request->get('email');
            $password = $request->request->get('password');
            
            // SQL query to find user by email
            $sql = "SELECT * FROM benutzer WHERE userEmail = :email";
            $stmt = $connection->prepare($sql);
            $stmt->bindParam(':email', $email);
            $stmt->execute();
            $user = $stmt->fetch();
            
            if ($user) {
                // Verify password
                if ($user['userPassword']) {
                    // Set session variables
                    $session->set('userID', $user['userID']);
                    $session->set('userName', $user['userName']);
                    $session->set('userRole', $user['userRole']);
                    
                    // Redirect to orders page
                    return $this->redirectToRoute('orders_page'); // Define this route later
                }
            } else {
                // Return error message if login fails
                $this->addFlash('error', 'E-Mail or password not correct. Please try again.');
            }
        }
        
        // Render login form (we'll create this template in step 2)
        return $this->render('login/index.html.twig');
    }
}

?>
