<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${formation == null ? 'Ajouter' : 'Modifier'} une Formation</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: #f5f5f5;
        }

        .navbar {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            padding: 15px 30px;
        }

        .navbar h1 {
            font-size: 24px;
        }

        .container {
            max-width: 600px;
            margin: 30px auto;
            padding: 0 20px;
        }

        .form-card {
            background: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
        }

        .form-header {
            margin-bottom: 30px;
        }

        .form-header h2 {
            color: #333;
            margin-bottom: 10px;
        }

        .form-header a {
            color: #667eea;
            text-decoration: none;
        }

        .form-group {
            margin-bottom: 20px;
        }

        .form-group label {
            display: block;
            color: #333;
            font-weight: 500;
            margin-bottom: 8px;
        }

        .form-group input, .form-group select, .form-group textarea {
            width: 100%;
            padding: 12px;
            border: 2px solid #e0e0e0;
            border-radius: 5px;
            font-size: 14px;
            transition: border-color 0.3s;
            font-family: inherit;
        }

        .form-group textarea {
            min-height: 100px;
            resize: vertical;
        }

        .form-group input:focus, .form-group select:focus, .form-group textarea:focus {
            outline: none;
            border-color: #667eea;
        }

        .error-message {
            background: #fee;
            color: #c33;
            padding: 10px;
            border-radius: 5px;
            margin-bottom: 20px;
        }

        .btn-group {
            display: flex;
            gap: 10px;
            margin-top: 30px;
        }

        .btn-submit, .btn-cancel {
            flex: 1;
            padding: 12px;
            border: none;
            border-radius: 5px;
            font-size: 16px;
            font-weight: 600;
            cursor: pointer;
            text-align: center;
            text-decoration: none;
            display: inline-block;
        }

        .btn-submit {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
        }

        .btn-cancel {
            background: #6c757d;
            color: white;
        }

        .btn-submit:hover {
            opacity: 0.9;
        }

        .btn-cancel:hover {
            background: #5a6268;
        }
    </style>
</head>
<body>
    <nav class="navbar">
        <h1>🎓 Gestion Formation</h1>
    </nav>

    <div class="container">
        <div class="form-card">
            <div class="form-header">
                <h2>${formation == null ? ' Ajouter' : ' Modifier'} une Formation</h2>
                <a href="${pageContext.request.contextPath}/admin/formations">← Retour à la liste</a>
            </div>

            <c:if test="${error != null}">
                <div class="error-message">
                    ${error}
                </div>
            </c:if>

            <form action="${pageContext.request.contextPath}/admin/formations" method="post">
                <input type="hidden" name="action" value="${formation == null ? 'add' : 'edit'}">
                <c:if test="${formation != null}">
                    <input type="hidden" name="id" value="${formation.id}">
                </c:if>

                <div class="form-group">
                    <label for="titre">Titre *</label>
                    <input type="text" id="titre" name="titre"
                           value="${formation != null ? formation.titre : ''}" required>
                </div>

                <div class="form-group">
                    <label for="duree">Durée (heures) *</label>
                    <input type="number" id="duree" name="duree" min="1"
                           value="${formation != null ? formation.duree : ''}" required>
                </div>

                <div class="form-group">
                    <label for="capacite">Capacité *</label>
                    <input type="number" id="capacite" name="capacite" min="1"
                           value="${formation != null ? formation.capacite : ''}" required>
                </div>

                <div class="form-group">
                    <label for="professeurId">Professeur *</label>
                    <select id="professeurId" name="professeurId" required>
                        <option value="">-- Sélectionner un professeur --</option>
                        <c:forEach var="prof" items="${professeurs}">
                            <option value="${prof.id}"
                                ${formation != null && formation.professeur != null &&
                                  formation.professeur.id == prof.id ? 'selected' : ''}>
                                ${prof.nom} ${prof.prenom}
                            </option>
                        </c:forEach>
                    </select>
                </div>

                <div class="form-group">
                    <label for="description">Description</label>
                    <textarea id="description" name="description">${formation != null ? formation.description : ''}</textarea>
                </div>

                <div class="btn-group">
                    <button type="submit" class="btn-submit">
                        ${formation == null ? ' Ajouter' : ' Enregistrer'}
                    </button>
                    <a href="${pageContext.request.contextPath}/admin/formations" class="btn-cancel">
                         Annuler
                    </a>
                </div>
            </form>
        </div>
    </div>
</body>
</html>