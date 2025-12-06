<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Centre de Formation - Accueil</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            min-height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
            padding: 20px;
        }

        .container {
            background: white;
            border-radius: 20px;
            box-shadow: 0 20px 60px rgba(0,0,0,0.3);
            overflow: hidden;
            max-width: 1200px;
            width: 100%;
        }

        .hero {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            padding: 60px 40px;
            text-align: center;
        }

        .hero h1 {
            font-size: 48px;
            margin-bottom: 20px;
            animation: fadeInDown 1s;
        }

        .hero p {
            font-size: 20px;
            opacity: 0.9;
            margin-bottom: 40px;
            animation: fadeInUp 1s;
        }

        .content {
            padding: 60px 40px;
        }

        .features {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
            gap: 30px;
            margin-bottom: 50px;
        }

        .feature-card {
            text-align: center;
            padding: 30px;
            border-radius: 10px;
            background: #f8f9fa;
            transition: transform 0.3s, box-shadow 0.3s;
        }

        .feature-card:hover {
            transform: translateY(-10px);
            box-shadow: 0 10px 30px rgba(0,0,0,0.1);
        }

        .feature-icon {
            font-size: 60px;
            margin-bottom: 20px;
        }

        .feature-card h3 {
            color: #333;
            margin-bottom: 15px;
            font-size: 22px;
        }

        .feature-card p {
            color: #666;
            line-height: 1.6;
        }

        .cta-section {
            text-align: center;
            padding: 40px;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            border-radius: 15px;
            color: white;
        }

        .cta-section h2 {
            font-size: 32px;
            margin-bottom: 20px;
        }

        .cta-section p {
            font-size: 18px;
            margin-bottom: 30px;
            opacity: 0.9;
        }

        .btn-login {
            display: inline-block;
            padding: 15px 40px;
            background: white;
            color: #667eea;
            text-decoration: none;
            border-radius: 50px;
            font-size: 18px;
            font-weight: 600;
            transition: all 0.3s;
            box-shadow: 0 5px 15px rgba(0,0,0,0.2);
        }

        .btn-login:hover {
            transform: translateY(-3px);
            box-shadow: 0 8px 25px rgba(0,0,0,0.3);
            background: #f8f9fa;
        }

        .stats {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(150px, 1fr));
            gap: 20px;
            margin: 40px 0;
        }

        .stat-item {
            text-align: center;
            padding: 20px;
            border-left: 4px solid #667eea;
        }

        .stat-number {
            font-size: 36px;
            font-weight: bold;
            color: #667eea;
            margin-bottom: 10px;
        }

        .stat-label {
            color: #666;
            font-size: 14px;
        }

        @keyframes fadeInDown {
            from {
                opacity: 0;
                transform: translateY(-30px);
            }
            to {
                opacity: 1;
                transform: translateY(0);
            }
        }

        @keyframes fadeInUp {
            from {
                opacity: 0;
                transform: translateY(30px);
            }
            to {
                opacity: 1;
                transform: translateY(0);
            }
        }

        @media (max-width: 768px) {
            .hero h1 {
                font-size: 32px;
            }

            .hero p {
                font-size: 16px;
            }

            .content {
                padding: 40px 20px;
            }

            .features {
                grid-template-columns: 1fr;
            }
        }
    </style>
</head>
<body>
<div class="container">
    <div class="hero">
        <h1>🎓 Centre de Formation</h1>
        <p>Plateforme de gestion complète pour votre centre de formation</p>
    </div>

    <div class="content">
        <div class="features">
            <div class="feature-card">
                <div class="feature-icon">👨‍💼</div>
                <h3>Espace Admin</h3>
                <p>Gérez l'ensemble du centre : apprenants, formations, professeurs et inscriptions</p>
            </div>

            <div class="feature-card">
                <div class="feature-icon">👨‍🏫</div>
                <h3>Espace Professeur</h3>
                <p>Consultez vos formations et suivez vos apprenants en temps réel</p>
            </div>

            <div class="feature-card">
                <div class="feature-icon">👨‍🎓</div>
                <h3>Espace Apprenant</h3>
                <p>Accédez à vos formations et suivez votre progression</p>
            </div>
        </div>

        <div class="stats">
            <div class="stat-item">
                <div class="stat-number">📚</div>
                <div class="stat-label">Formations variées</div>
            </div>
            <div class="stat-item">
                <div class="stat-number">🎯</div>
                <div class="stat-label">Suivi personnalisé</div>
            </div>
            <div class="stat-item">
                <div class="stat-number">⚡</div>
                <div class="stat-label">Gestion simplifiée</div>
            </div>
            <div class="stat-item">
                <div class="stat-number">🔒</div>
                <div class="stat-label">Sécurisé</div>
            </div>
        </div>

        <div class="cta-section">
            <h2>Prêt à commencer ?</h2>
            <p>Connectez-vous à votre espace pour accéder à toutes les fonctionnalités</p>
            <a href="${pageContext.request.contextPath}/login" class="btn-login">
                🔐 Se connecter
            </a>
        </div>
    </div>
</div>
</body>
</html>