package org.example.rukh.controller;

import org.example.rukh.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collections;
import java.util.Date;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @PostMapping("/createNews")
    public ResponseEntity<?> createNews(@RequestParam("content") String content,
                                        @RequestParam("discipline") String discipline,
                                        @RequestParam("title") String title,
                                        @RequestParam("image") MultipartFile image,
                                        @RequestParam("tournament") int tournament_id,
                                        @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails==null||userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .noneMatch(role -> role.equals("ROLE_ADMIN"))) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Not admin");
        } else {
            String error = adminService.validateNewsData(discipline, content, title, image, tournament_id);
            if (error == null) {
                return ResponseEntity.status(HttpStatus.CREATED).body("Success");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("error", error));
            }
        }
    }
    @PutMapping("/updateNews/{id}")
    public ResponseEntity<?> updateNews(@AuthenticationPrincipal UserDetails userDetails,
                                        @RequestParam("content") String content,
                                        @RequestParam("discipline") String discipline,
                                        @RequestParam("title") String title,
                                        @RequestParam("image") MultipartFile image,
                                        @PathVariable("id") int id) {
        if (userDetails==null||userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .noneMatch(role -> role.equals("ROLE_ADMIN"))) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Not admin");
        } else {
            String error = adminService.updateNewsData(id, discipline, content, title, image);
            if (error == null) {
                return ResponseEntity.status(HttpStatus.CREATED).body("Success");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("error", error));
            }
        }
    }
    @DeleteMapping("/deleteNews/{id}")
    public ResponseEntity<?> deleteNews(@AuthenticationPrincipal UserDetails userDetails,
                                        @PathVariable("id") int id) {
        if (userDetails==null||userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .noneMatch(role -> role.equals("ROLE_ADMIN"))) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Not admin");
        } else {
            String error = adminService.deleteNews(id);
            if (error == null) {
                return ResponseEntity.status(HttpStatus.CREATED).body("Success");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("error", error));
            }
        }
    }
    @PostMapping("/createTeam")
    public ResponseEntity<?> createTeam(@RequestParam("content") String content,
                                        @RequestParam("discipline") String discipline,
                                        @RequestParam("name") String name,
                                        @RequestParam("image") MultipartFile image,
                                        @RequestParam("rukh") boolean rukh,
                                        @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails==null||userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .noneMatch(role -> role.equals("ROLE_ADMIN"))) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Not admin");
        } else {
            String error = adminService.validateTeamData(discipline, content, name, image, rukh);
            if (error == null) {
                return ResponseEntity.status(HttpStatus.CREATED).body("Success");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("error", error));
            }
        }
    }
    @PutMapping("/updateTeam/{id}")
    public ResponseEntity<?> updateTeam(@AuthenticationPrincipal UserDetails userDetails,
                                        @RequestParam("content") String content,
                                        @RequestParam("discipline") String discipline,
                                        @RequestParam("name") String name,
                                        @RequestParam("image") MultipartFile image,
                                        @PathVariable("id") int id) {
        if (userDetails==null||userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .noneMatch(role -> role.equals("ROLE_ADMIN"))) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Not admin");
        } else {
            String error = adminService.updateTeamData(id, discipline, content, name, image);
            if (error == null) {
                return ResponseEntity.status(HttpStatus.CREATED).body("Success");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("error", error));
            }
        }
    }
    @DeleteMapping("/deleteTeam/{id}")
    public ResponseEntity<?> deleteTeam(@AuthenticationPrincipal UserDetails userDetails,
                                        @PathVariable("id") int id) {
        if (userDetails==null||userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .noneMatch(role -> role.equals("ROLE_ADMIN"))) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Not admin");
        } else {
            String error = adminService.deleteTeam(id);
            if (error == null) {
                return ResponseEntity.status(HttpStatus.CREATED).body("Success");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("error", error));
            }
        }
    }
    @PostMapping("/createPlayer")
    public ResponseEntity<?> createPlayer(@RequestParam("nickname") String nickname,
                                          @RequestParam("name") String name,
                                          @RequestParam("content") String content,
                                        @RequestParam("image") MultipartFile image,
                                          @RequestParam("team_id") int team_id,
                                          @RequestParam("socialMediaLinks") String socialMediaLinks,
                                        @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails==null||userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .noneMatch(role -> role.equals("ROLE_ADMIN"))) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Not admin");
        } else {
            String error = adminService.validatePlayerData(nickname, name, content, image, team_id, socialMediaLinks);
            if (error == null) {
                return ResponseEntity.status(HttpStatus.CREATED).body("Success");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("error", error));
            }
        }
    }
    @PutMapping("/updatePlayer/{id}")
    public ResponseEntity<?> updatePlayer(@RequestParam("nickname") String nickname,
                                          @RequestParam("name") String name,
                                          @RequestParam("content") String content,
                                          @RequestParam("image") MultipartFile image,
                                          @RequestParam("team_id") int team_id,
                                          @RequestParam("socialMediaLinks") String socialMediaLinks,
                                          @AuthenticationPrincipal UserDetails userDetails,
                                        @PathVariable("id") int id) {
        if (userDetails==null||userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .noneMatch(role -> role.equals("ROLE_ADMIN"))) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Not admin");
        } else {
            String error = adminService.updatePlayerData(id, nickname, name, content, image, team_id, socialMediaLinks);
            if (error == null) {
                return ResponseEntity.status(HttpStatus.CREATED).body("Success");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("error", error));
            }
        }
    }
    @DeleteMapping("/deletePlayer/{id}")
    public ResponseEntity<?> deletePlayer(@AuthenticationPrincipal UserDetails userDetails,
                                        @PathVariable("id") int id) {
        if (userDetails==null||userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .noneMatch(role -> role.equals("ROLE_ADMIN"))) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Not admin");
        } else {
            String error = adminService.deletePlayer(id);
            if (error == null) {
                return ResponseEntity.status(HttpStatus.CREATED).body("Success");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("error", error));
            }
        }
    }
    @PostMapping("/createTournament")
    public ResponseEntity<?> createTournament(@RequestParam("name") String name,
                                              @RequestParam("date")String date,
                                              @RequestParam("content")String content,
                                              @RequestParam("image") MultipartFile image,
                                              @RequestParam("discipline") String discipline,
                                              @RequestParam("status") String status,
                                              @RequestParam("result") String result,
                                              @RequestParam("prizepool") String prizepool,
                                              @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails==null||userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .noneMatch(role -> role.equals("ROLE_ADMIN"))) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Not admin");
        } else {
            String error = adminService.validateTournamentData(name, date, content, image, discipline, status, result, prizepool);
            if (error == null) {
                return ResponseEntity.status(HttpStatus.CREATED).body("Success");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("error", error));
            }
        }
    }
    @PutMapping("/updateTournament/{id}")
    public ResponseEntity<?> updateTournament(@RequestParam("name") String name,
                                              @RequestParam("date")String date,
                                              @RequestParam("content")String content,
                                              @RequestParam("image") MultipartFile image,
                                              @RequestParam("discipline") String discipline,
                                              @RequestParam("status") String status,
                                              @RequestParam("result") String result,
                                              @RequestParam("prizepool") String prizepool,
                                              @AuthenticationPrincipal UserDetails userDetails,
                                              @PathVariable("id")int id) {
        if (userDetails==null||userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .noneMatch(role -> role.equals("ROLE_ADMIN"))) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Not admin");
        } else {
            String error = adminService.updateTournamentData(id, name, date, content, image, discipline, status, result, prizepool);
            if (error == null) {
                return ResponseEntity.status(HttpStatus.CREATED).body("Success");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("error", error));
            }
        }
    }
    @DeleteMapping("/deleteTournament/{id}")
    public ResponseEntity<?> deleteTournament(@AuthenticationPrincipal UserDetails userDetails,
                                          @PathVariable("id") int id) {
        if (userDetails==null||userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .noneMatch(role -> role.equals("ROLE_ADMIN"))) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Not admin");
        } else {
            String error = adminService.deleteTournament(id);
            if (error == null) {
                return ResponseEntity.status(HttpStatus.CREATED).body("Success");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("error", error));
            }
        }
    }
    @PostMapping("/createMatch")
    public ResponseEntity<?> createMatch(@RequestParam("title") String title,
                                         @RequestParam("date")String date,
                                         @RequestParam("discipline") String discipline,
                                         @RequestParam("image") MultipartFile image,
                                         @RequestParam("result") String result,
                                         @RequestParam("status") String status,
                                         @RequestParam("youtubeUrl")String youtubeUrl,
                                         @RequestParam("tournamentId") int tournamentId,
                                         @RequestParam("team1Id") int team1Id,
                                         @RequestParam("team2Id") int team2Id,
                                         @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails==null||userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .noneMatch(role -> role.equals("ROLE_ADMIN"))) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Not admin");
        } else {
            String error = adminService.validateMatchData(title, date, discipline, image, result, status, youtubeUrl,tournamentId, team1Id, team2Id);
            if (error == null) {
                return ResponseEntity.status(HttpStatus.CREATED).body("Success");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("error", error));
            }
        }
    }
    @PutMapping("/updateMatch/{id}")
    public ResponseEntity<?> updateMatch(@RequestParam("title") String title,
                                         @RequestParam("date")String date,
                                         @RequestParam("discipline") String discipline,
                                         @RequestParam("image") MultipartFile image,
                                         @RequestParam("result") String result,
                                         @RequestParam("status") String status,
                                         @RequestParam("youtubeUrl")String youtubeUrl,
                                         @RequestParam("tournamentId") int tournamentId,
                                         @RequestParam("team1Id") int team1Id,
                                         @RequestParam("team2Id") int team2Id,
                                         @AuthenticationPrincipal UserDetails userDetails,
                                         @PathVariable("id")int id) {
        if (userDetails==null||userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .noneMatch(role -> role.equals("ROLE_ADMIN"))) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Not admin");
        } else {
            String error = adminService.updateMatchData(id, title, date, discipline, image, result, status, youtubeUrl, tournamentId, team1Id, team2Id);
            if (error == null) {
                return ResponseEntity.status(HttpStatus.CREATED).body("Success");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("error", error));
            }
        }
    }
    @DeleteMapping("/deleteMatch/{id}")
    public ResponseEntity<?> deleteMatch(@AuthenticationPrincipal UserDetails userDetails,
                                              @PathVariable("id") int id) {
        if (userDetails==null||userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .noneMatch(role -> role.equals("ROLE_ADMIN"))) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Not admin");
        } else {
            String error = adminService.deleteMatch(id);
            if (error == null) {
                return ResponseEntity.status(HttpStatus.CREATED).body("Success");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("error", error));
            }
        }
    }
    @PostMapping("/uploadSliderImage")
    public ResponseEntity<?> uploadSliderImage(@RequestParam("image") MultipartFile image,
                                            @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails==null||userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .noneMatch(role -> role.equals("ROLE_ADMIN"))) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Not admin");
        } else {
            String error = adminService.uploadSliderImage(image);
            if (error == null) {
                return ResponseEntity.status(HttpStatus.CREATED).body("Success");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("error", error));
            }
        }
    }
    @PutMapping("/updateSliderImage")
    public ResponseEntity<?> updateSliderImage(@RequestParam("image") MultipartFile image,
                                         @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails==null||userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .noneMatch(role -> role.equals("ROLE_ADMIN"))) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Not admin");
        } else {
            String error = adminService.updateSliderImage(image);
            if (error == null) {
                return ResponseEntity.status(HttpStatus.CREATED).body("Success");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("error", error));
            }
        }
    }
    @PostMapping("/uploadTopImage")
    public ResponseEntity<?> uploadTopImage(@RequestParam("image") MultipartFile image,
                                               @RequestParam("page") String page,
                                               @RequestParam("tab") String tab,
                                               @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails==null||userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .noneMatch(role -> role.equals("ROLE_ADMIN"))) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Not admin");
        } else {
            String error = adminService.uploadTopImage(image, page, tab);
            if (error == null) {
                return ResponseEntity.status(HttpStatus.CREATED).body("Success");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("error", error));
            }
        }
    }
    @PutMapping("/updateTopImage")
    public ResponseEntity<?> updateTopImage(@RequestParam("image") MultipartFile image,
                                               @RequestParam("page") String page,
                                               @RequestParam("tab") String tab,
                                               @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails==null||userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .noneMatch(role -> role.equals("ROLE_ADMIN"))) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Not admin");
        } else {
            String error = adminService.updateTopImage(image, page, tab);
            if (error == null) {
                return ResponseEntity.status(HttpStatus.CREATED).body("Success");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("error", error));
            }
        }
    }
    @DeleteMapping("/deleteComment/{id}")
    public ResponseEntity<?> deleteComment(@AuthenticationPrincipal UserDetails userDetails,
                                         @PathVariable("id") int id) {
        if (userDetails==null||userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .noneMatch(role -> role.equals("ROLE_ADMIN"))) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Not admin");
        } else {
            String error = adminService.deleteComment(id);
            if (error == null) {
                return ResponseEntity.status(HttpStatus.CREATED).body("Success");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("error", error));
            }
        }
    }

}
